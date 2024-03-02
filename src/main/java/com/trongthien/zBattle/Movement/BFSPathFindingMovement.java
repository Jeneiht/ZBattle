package com.trongthien.zBattle.Movement;

import com.trongthien.zBattle.GameMap.GameMap;
import com.trongthien.zBattle.character.Direction;
import com.trongthien.zBattle.character.Entity;
import com.trongthien.zBattle.character.Player;
import com.trongthien.zBattle.component.CollisionChecker;
import com.trongthien.zBattle.component.SharedContext;
import com.trongthien.zBattle.constant.GameConstant;

import java.util.Queue;

public class BFSPathFindingMovement implements Movement {
    private Node start;
    private Node goal;

    @Override
    public void move(Entity entity) {
        Player player = SharedContext.getInstance().getCurrentPlayer();
        GameMap gameMap = SharedContext.getInstance().getCurrentGameMap();
        Node nodes[][] = new Node[gameMap.getMaxRow()][gameMap.getMaxCol()];
        for (int i = 0; i < gameMap.getMaxRow(); i++) {
            for (int j = 0; j < gameMap.getMaxCol(); j++) {
                nodes[i][j] = new Node(j, i);
            }
        }
        start = nodes[(entity.getHitBox().getY() + entity.getHitBox().getHeight() / 2) / gameMap.getTileSize()][(entity.getHitBox().getX() + entity.getHitBox().getWidth() / 2) / gameMap.getTileSize()];
        goal = nodes[(player.getHitBox().getY() + player.getHitBox().getHeight() / 2) / gameMap.getTileSize()][(player.getHitBox().getX() + player.getHitBox().getWidth() / 2) / gameMap.getTileSize()];
        if (start.equals(goal)) {
            Movement movement = new LocalPathFindingMovement();
            movement.move(entity);
            return;
        }
        Queue<Node> openSet = new java.util.LinkedList<>();
        //do BFS
        openSet.add(start);
        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            if (current.equals(goal)) {
                break;
            }
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int x = current.getX() + i;
                    int y = current.getY() + j;
                    if (x >= 0 && x < gameMap.getMaxCol() && y >= 0 && y < gameMap.getMaxRow() && !gameMap.getTiles().get(0).get(y).get(x).isSolid()) {
                        Node neighbor = nodes[y][x];
                        if (neighbor.getParent() == null && !neighbor.equals(current)) {
                            neighbor.setParent(current);
                            openSet.add(neighbor);
                        }
                    }
                }
            }
        }
        //if no path found
        if (goal.getParent() == null) {
            return;
        }
        //reconstruct path
        Node next = start;
        Node current = goal;
        while (!current.equals(start)) {
            if (current.getParent() == start) {
                next = current;
                break;
            }
            current = current.getParent();
        }
        Direction direction = Direction.DOWN_LEFT;
        if (next.getX() >= start.getX() && next.getY() >= start.getY()) {
            direction = Direction.DOWN_RIGHT;
        }
        if (next.getX() < start.getX() && next.getY() >= start.getY()) {
            direction = Direction.DOWN_LEFT;
        }
        if (next.getX() >= start.getX() && next.getY() < start.getY()) {
            direction = Direction.UP_RIGHT;
        }
        if (next.getX() < start.getX() && next.getY() < start.getY()) {
            direction = Direction.UP_LEFT;
        }
        entity.setDirection(direction);
        int speed = entity.getSpeed();
        switch (direction) {
            case UP:
                entity.setY(entity.getY() - speed);
                while (CollisionChecker.getInstance().checkCollisionTop(entity.getHitBox())) {
                    entity.setY(entity.getY() + GameConstant.minSpeed);
                }
                break;
            case DOWN:
                entity.setY(entity.getY() + speed);
                while (CollisionChecker.getInstance().checkCollisionBottom(entity.getHitBox())) {
                    entity.setY(entity.getY() - GameConstant.minSpeed);
                }
                break;
            case LEFT:
                entity.setX(entity.getX() - speed);
                while (CollisionChecker.getInstance().checkCollisionLeft(entity.getHitBox())) {
                    entity.setX(entity.getX() + GameConstant.minSpeed);
                }
                break;
            case RIGHT:
                entity.setX(entity.getX() + speed);
                while (CollisionChecker.getInstance().checkCollisionRight(entity.getHitBox())) {
                    entity.setX(entity.getX() - GameConstant.minSpeed);
                }
                break;
            case UP_LEFT:
                entity.setY(entity.getY() - speed / 2);
                while (CollisionChecker.getInstance().checkCollisionTop(entity.getHitBox())) {
                    entity.setY(entity.getY() + GameConstant.minSpeed);
                }
                entity.setX(entity.getX() - speed / 2);
                while (CollisionChecker.getInstance().checkCollisionLeft(entity.getHitBox())) {
                    entity.setX(entity.getX() + GameConstant.minSpeed);
                }
                break;
            case UP_RIGHT:
                entity.setY(entity.getY() - speed / 2);
                while (CollisionChecker.getInstance().checkCollisionTop(entity.getHitBox())) {
                    entity.setY(entity.getY() + GameConstant.minSpeed);
                }
                entity.setX(entity.getX() + speed / 2);
                while (CollisionChecker.getInstance().checkCollisionRight(entity.getHitBox())) {
                    entity.setX(entity.getX() - GameConstant.minSpeed);
                }
                break;
            case DOWN_LEFT:
                entity.setY(entity.getY() + speed / 2);
                while (CollisionChecker.getInstance().checkCollisionBottom(entity.getHitBox())) {
                    entity.setY(entity.getY() - GameConstant.minSpeed);
                }
                entity.setX(entity.getX() - speed / 2);
                while (CollisionChecker.getInstance().checkCollisionLeft(entity.getHitBox())) {
                    entity.setX(entity.getX() + GameConstant.minSpeed);
                }
                break;
            case DOWN_RIGHT:
                entity.setY(entity.getY() + speed / 2);
                while (CollisionChecker.getInstance().checkCollisionBottom(entity.getHitBox())) {
                    entity.setY(entity.getY() - GameConstant.minSpeed);
                }
                entity.setX(entity.getX() + speed / 2);
                while (CollisionChecker.getInstance().checkCollisionRight(entity.getHitBox())) {
                    entity.setX(entity.getX() - GameConstant.minSpeed);
                }
                break;
        }

    }
}

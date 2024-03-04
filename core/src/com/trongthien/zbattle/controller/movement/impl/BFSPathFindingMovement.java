package com.trongthien.zbattle.controller.movement.impl;

import com.trongthien.zbattle.controller.movement.Movement;
import com.trongthien.zbattle.controller.movement.Node;
import com.trongthien.zbattle.view.map.GameMap;
import com.trongthien.zbattle.controller.movement.Direction;
import com.trongthien.zbattle.model.Entity;
import com.trongthien.zbattle.model.Player;
import com.trongthien.zbattle.controller.CollisionChecker;
import com.trongthien.zbattle.common.SharedContext;
import com.trongthien.zbattle.common.constant.GameConstant;

import java.util.Queue;

public class BFSPathFindingMovement implements Movement {

    @Override
    public void move(Entity entity) {
        Player player = SharedContext.getInstance().getCurrentPlayer();
        GameMap gameMap = SharedContext.getInstance().getCurrentGameMap();
        Node[][] nodes = new Node[gameMap.getMaxRow()][gameMap.getMaxCol()];
        for (int i = 0; i < gameMap.getMaxRow(); i++) {
            for (int j = 0; j < gameMap.getMaxCol(); j++) {
                nodes[i][j] = new Node(i, j);
            }
        }
        int entityCenterY = entity.getHitBox().getY() + entity.getHitBox().getHeight() / 2;
        int entityCenterX = entity.getHitBox().getX() + entity.getHitBox().getWidth() / 2;
        int entityTileY = entityCenterY / gameMap.getTileSize();
        int entityTileX = entityCenterX / gameMap.getTileSize();

        int playerCenterY = player.getHitBox().getY() + player.getHitBox().getHeight() / 2;
        int playerCenterX = player.getHitBox().getX() + player.getHitBox().getWidth() / 2;
        int playerTileY = playerCenterY / gameMap.getTileSize();
        int playerTileX = playerCenterX / gameMap.getTileSize();

        Node start = nodes[entityTileY][entityTileX];
        Node goal = nodes[playerTileY][playerTileX];
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
                    int newI = current.getX() + i;
                    int newJ = current.getY() + j;
                    if (newI >= 0 && newI < gameMap.getMaxCol() && newJ >= 0 && newJ < gameMap.getMaxRow()
                            && !gameMap.isSolidTile(newJ * gameMap.getTileSize(), newI * gameMap.getTileSize())) {
                        Node neighbor = nodes[newI][newJ];
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
        int nextX= next.getY() * gameMap.getTileSize() + gameMap.getTileSize() / 2;
        int nextY = next.getX() * gameMap.getTileSize() + gameMap.getTileSize() / 2;
        Direction direction = Direction.DOWN_LEFT;
        if (nextX >= entityCenterX && nextY >= entityCenterY) {
            direction = Direction.UP_RIGHT;
        }
        if (nextX >= entityCenterX && nextY <= entityCenterY) {
            direction = Direction.DOWN_RIGHT;
        }
        if (nextX < entityCenterX && nextY >= entityCenterY) {
            direction = Direction.UP_LEFT;
        }
        entity.setDirection(direction);
        int speed = entity.getSpeed();
        switch (direction) {
            case UP_LEFT:
                entity.setY(entity.getY() + speed / 2);
                while (CollisionChecker.getInstance().checkCollisionTop(entity.getHitBox())) {
                    entity.setY(entity.getY() - GameConstant.minSpeed);
                }
                entity.setX(entity.getX() - speed / 2);
                while (CollisionChecker.getInstance().checkCollisionLeft(entity.getHitBox())) {
                    entity.setX(entity.getX() + GameConstant.minSpeed);
                }
                break;
            case UP_RIGHT:
                entity.setY(entity.getY() + speed / 2);
                while (CollisionChecker.getInstance().checkCollisionTop(entity.getHitBox())) {
                    entity.setY(entity.getY() - GameConstant.minSpeed);
                }
                entity.setX(entity.getX() + speed / 2);
                while (CollisionChecker.getInstance().checkCollisionRight(entity.getHitBox())) {
                    entity.setX(entity.getX() - GameConstant.minSpeed);
                }
                break;
            case DOWN_LEFT:
                entity.setY(entity.getY() - speed / 2);
                while (CollisionChecker.getInstance().checkCollisionBottom(entity.getHitBox())) {
                    entity.setY(entity.getY() + GameConstant.minSpeed);
                }
                entity.setX(entity.getX() - speed / 2);
                while (CollisionChecker.getInstance().checkCollisionLeft(entity.getHitBox())) {
                    entity.setX(entity.getX() + GameConstant.minSpeed);
                }
                break;
            case DOWN_RIGHT:
                entity.setY(entity.getY() - speed / 2);
                while (CollisionChecker.getInstance().checkCollisionBottom(entity.getHitBox())) {
                    entity.setY(entity.getY() + GameConstant.minSpeed);
                }
                entity.setX(entity.getX() + speed / 2);
                while (CollisionChecker.getInstance().checkCollisionRight(entity.getHitBox())) {
                    entity.setX(entity.getX() - GameConstant.minSpeed);
                }
                break;
        }

    }
}

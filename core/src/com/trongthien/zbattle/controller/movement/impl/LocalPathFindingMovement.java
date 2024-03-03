package com.trongthien.zbattle.controller.movement.impl;

import com.trongthien.zbattle.controller.movement.Direction;
import com.trongthien.zbattle.model.Entity;
import com.trongthien.zbattle.controller.movement.Movement;
import com.trongthien.zbattle.controller.CollisionChecker;
import com.trongthien.zbattle.common.SharedContext;
import com.trongthien.zbattle.common.constant.GameConstant;

public class LocalPathFindingMovement implements Movement {
    private float goalX, goalY;

    @Override
    public void move(Entity entity) {
        setGoal(SharedContext.getInstance().getCurrentPlayer());
        float speed = entity.getSpeed() / GameConstant.speedMultiplier;
        float x = (entity.getX() + entity.getWidth()) / 2;
        float y = (entity.getY() + entity.getHeight()) / 2;
        if (x == goalX && y == goalY) {
            return;
        }
        Direction direction = entity.getDirection();
        if (x < goalX && y < goalY) {
            direction = Direction.DOWN_RIGHT;
        }
        if (x < goalX && y > goalY) {
            direction = Direction.UP_RIGHT;
        }
        if (x > goalX && y < goalY) {
            direction = Direction.DOWN_LEFT;
        }
        if (x > goalX && y > goalY) {
            direction = Direction.UP_LEFT;
        }
        if (x == goalX && y < goalY) {
            direction = Direction.DOWN;
        }
        if (x == goalX && y > goalY) {
            direction = Direction.UP;
        }
        if (x < goalX && y == goalY) {
            direction = Direction.RIGHT;
        }
        if (x > goalX && y == goalY) {
            direction = Direction.LEFT;
        }
        entity.setDirection(direction);
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

    private void setGoal(Entity entity) {
        goalX = (entity.getX() + entity.getWidth()) / 2;
        goalY = (entity.getY() + entity.getHeight()) / 2;
    }
}

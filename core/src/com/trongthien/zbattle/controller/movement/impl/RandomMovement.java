package com.trongthien.zbattle.controller.movement.impl;

import com.trongthien.zbattle.controller.movement.Direction;
import com.trongthien.zbattle.model.Entity;
import com.trongthien.zbattle.controller.movement.Movement;
import com.trongthien.zbattle.controller.CollisionChecker;
import com.trongthien.zbattle.common.constant.GameConstant;
import lombok.var;

public class RandomMovement implements Movement {
    //Singleton
    private static RandomMovement instance;
    private RandomMovement(){}
    public static RandomMovement getInstance(){
        if(instance==null){
            instance = new RandomMovement();
        }
        return instance;
    }
    @Override
    public void move(Entity entity) {
        boolean isCollision = false;
        int speed = entity.getSpeed();
        Direction direction = entity.getDirection();
        switch (direction) {
            case UP:
                entity.setY(entity.getY() + speed);
                while (CollisionChecker.getInstance().checkCollisionTop(entity.getHitBox())) {
                    isCollision = true;
                    entity.setY(entity.getY() - GameConstant.minSpeed);
                }
                break;
            case DOWN:
                entity.setY(entity.getY() - speed);
                while (CollisionChecker.getInstance().checkCollisionBottom(entity.getHitBox())) {
                    isCollision = true;
                    entity.setY(entity.getY() + GameConstant.minSpeed);
                }
                break;
            case LEFT:
                entity.setX(entity.getX() - speed);
                while (CollisionChecker.getInstance().checkCollisionLeft(entity.getHitBox())) {
                    isCollision = true;
                    entity.setX(entity.getX() + GameConstant.minSpeed);
                }
                break;
            case RIGHT:
                entity.setX(entity.getX() + speed);
                while (CollisionChecker.getInstance().checkCollisionRight(entity.getHitBox())) {
                    isCollision = true;
                    entity.setX(entity.getX() - GameConstant.minSpeed);}
                break;
            case UP_LEFT:
                entity.setY(entity.getY() + speed / 2);
                while (CollisionChecker.getInstance().checkCollisionTop(entity.getHitBox())) {
                    isCollision = true;
                    entity.setY(entity.getY() - GameConstant.minSpeed);
                }
                entity.setX(entity.getX() - speed / 2);
                while (CollisionChecker.getInstance().checkCollisionLeft(entity.getHitBox())) {
                    isCollision = true;
                    entity.setX(entity.getX() + GameConstant.minSpeed);
                }
                break;
            case UP_RIGHT:
                entity.setY(entity.getY() + speed / 2);
                while (CollisionChecker.getInstance().checkCollisionTop(entity.getHitBox())) {
                    isCollision = true;
                    entity.setY(entity.getY() - GameConstant.minSpeed);
                }
                entity.setX(entity.getX() + speed / 2);
                while (CollisionChecker.getInstance().checkCollisionRight(entity.getHitBox())) {
                    isCollision = true;
                    entity.setX(entity.getX() - GameConstant.minSpeed);
                }
                break;
            case DOWN_LEFT:
                entity.setY(entity.getY() - speed / 2);
                while (CollisionChecker.getInstance().checkCollisionBottom(entity.getHitBox())) {
                    isCollision = true;
                    entity.setY(entity.getY() + GameConstant.minSpeed);
                }
                entity.setX(entity.getX() - speed / 2);
                while (CollisionChecker.getInstance().checkCollisionLeft(entity.getHitBox())) {
                    isCollision = true;
                    entity.setX(entity.getX() + GameConstant.minSpeed);
                }
                break;
            case DOWN_RIGHT:
                entity.setY(entity.getY() - speed / 2);
                while (CollisionChecker.getInstance().checkCollisionBottom(entity.getHitBox())) {
                    isCollision = true;
                    entity.setY(entity.getY() + GameConstant.minSpeed);
                }
                entity.setX(entity.getX() + speed / 2);
                while (CollisionChecker.getInstance().checkCollisionRight(entity.getHitBox())) {
                    isCollision = true;
                    entity.setX(entity.getX() - GameConstant.minSpeed);
                }
                break;
        }
        if(isCollision) {
            entity.setDirection(Direction.getRandom());
        }
    }
}

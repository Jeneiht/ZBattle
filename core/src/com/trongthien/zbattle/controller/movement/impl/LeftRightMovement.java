package com.trongthien.zbattle.controller.movement.impl;

import com.trongthien.zbattle.controller.CollisionChecker;
import com.trongthien.zbattle.controller.movement.Direction;
import com.trongthien.zbattle.controller.movement.Movement;
import com.trongthien.zbattle.model.Entity;

public class LeftRightMovement implements Movement {
    //singleton
    private static LeftRightMovement instance;
    private LeftRightMovement() {
    }
    public static LeftRightMovement getInstance() {
        if (instance == null) {
            instance = new LeftRightMovement();
        }
        return instance;
    }
    @Override
    public void move(Entity entity) {
        boolean isCollision = false;
        entity.setDirection(Direction.force(entity.getDirection()));
        Direction direction = entity.getDirection();
        if(direction == Direction.LEFT) {
            entity.setX(entity.getX() - entity.getSpeed());
            while(CollisionChecker.getInstance().checkCollisionLeft(entity.getHitBox())) {
                isCollision = true;
                entity.setX(entity.getX() + 1);
            }
        }
        else if(direction == Direction.RIGHT) {
            entity.setX(entity.getX() + entity.getSpeed());
            while (CollisionChecker.getInstance().checkCollisionRight(entity.getHitBox())) {
                isCollision = true;
                entity.setX(entity.getX() - 1);
            }
        }
        if(isCollision) {
            entity.setDirection(Direction.reverse(direction));
        }
    }
}

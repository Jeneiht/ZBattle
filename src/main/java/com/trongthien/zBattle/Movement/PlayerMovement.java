package com.trongthien.zBattle.Movement;

import com.trongthien.zBattle.character.Direction;
import com.trongthien.zBattle.character.Entity;
import com.trongthien.zBattle.component.CollisionChecker;
import com.trongthien.zBattle.constant.GameConstant;
import com.trongthien.zBattle.key.KeyHandler;

public class PlayerMovement implements Movement{
    @Override
    public void move(Entity entity) {
        int speed = entity.getSpeed();
        Direction direction = entity.getDirection();
        if (KeyHandler.getInstance().isUp()) {
            direction = Direction.UP;
        }
        if (KeyHandler.getInstance().isDown()) {
            direction = Direction.DOWN;
        }
        if (KeyHandler.getInstance().isLeft()) {
            direction = Direction.LEFT;
        }
        if (KeyHandler.getInstance().isRight()) {
            direction = Direction.RIGHT;
        }
        if (KeyHandler.getInstance().isUp() && KeyHandler.getInstance().isLeft() && !KeyHandler.getInstance().isRight() && !KeyHandler.getInstance().isDown()) {
            direction = Direction.UP_LEFT;
        }
        if (KeyHandler.getInstance().isUp() && KeyHandler.getInstance().isRight() && !KeyHandler.getInstance().isLeft() && !KeyHandler.getInstance().isDown()) {
            direction = Direction.UP_RIGHT;
        }
        if (KeyHandler.getInstance().isDown() && KeyHandler.getInstance().isLeft() && !KeyHandler.getInstance().isRight() && !KeyHandler.getInstance().isUp()) {
            direction = Direction.DOWN_LEFT;
        }
        if (KeyHandler.getInstance().isDown() && KeyHandler.getInstance().isRight() && !KeyHandler.getInstance().isLeft() && !KeyHandler.getInstance().isUp()) {
            direction = Direction.DOWN_RIGHT;
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
                    entity.setX(entity.getX() - GameConstant.minSpeed);}
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

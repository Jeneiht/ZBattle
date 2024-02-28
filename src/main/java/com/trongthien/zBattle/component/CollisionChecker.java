package com.trongthien.zBattle.component;

import com.trongthien.zBattle.GameMap.GameMap;
import com.trongthien.zBattle.character.Entity;

public class CollisionChecker {
    public GameMap gameMap;

    public CollisionChecker(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public boolean checkCollisionTop(Entity entity) {
        if (gameMap.isSolidTile(entity.getX() + entity.bodyHitBox.getX(), entity.getY() + entity.bodyHitBox.getY()) || gameMap.isSolidTile(entity.getX() + entity.bodyHitBox.getX() + entity.bodyHitBox.getWidth(), entity.getY() + entity.bodyHitBox.getY())) {
            return true;
        }
        return false;
    }
    public boolean checkCollisionBottom(Entity entity) {
        if (gameMap.isSolidTile(entity.getX() + entity.bodyHitBox.getX(), entity.getY() + entity.bodyHitBox.getY() + entity.bodyHitBox.getHeight()) || gameMap.isSolidTile(entity.getX() + entity.bodyHitBox.getX() + entity.bodyHitBox.getWidth(), entity.getY() + entity.bodyHitBox.getY() + entity.bodyHitBox.getHeight())) {
            return true;
        }
        return false;
    }
    public boolean checkCollisionLeft(Entity entity) {
        if (gameMap.isSolidTile(entity.getX() + entity.bodyHitBox.getX(), entity.getY() + entity.bodyHitBox.getY()) || gameMap.isSolidTile(entity.getX() + entity.bodyHitBox.getX(), entity.getY() + entity.bodyHitBox.getY() + entity.bodyHitBox.getHeight())) {
            return true;
        }
        return false;
    }
    public boolean checkCollisionRight(Entity entity) {
        if (gameMap.isSolidTile(entity.getX() + entity.bodyHitBox.getX() + entity.bodyHitBox.getWidth(), entity.getY() + entity.bodyHitBox.getY()) || gameMap.isSolidTile(entity.getX() + entity.bodyHitBox.getX() + entity.bodyHitBox.getWidth(), entity.getY() + entity.bodyHitBox.getY() + entity.bodyHitBox.getHeight())) {
            return true;
        }
        return false;
    }
}

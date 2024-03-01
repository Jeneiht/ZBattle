package com.trongthien.zBattle.component;

import com.trongthien.zBattle.GameMap.GameMap;
import com.trongthien.zBattle.character.Entity;
import com.trongthien.zBattle.character.HitBox;

public class CollisionChecker {
    //Singleton
    private static CollisionChecker collisionChecker = new CollisionChecker();
    public GameMap gameMap;

    private CollisionChecker() {
        gameMap = SharedCurrentContext.getInstance().getCurrentGameMap();
    }
    public static CollisionChecker getInstance() {
        if(collisionChecker == null) {
            collisionChecker = new CollisionChecker();
        }
        return collisionChecker;
    }

    public boolean checkCollisionTop(HitBox hitBox) {
        if (gameMap.isSolidTile(hitBox.getX(), hitBox.getY()) || gameMap.isSolidTile(hitBox.getX() + hitBox.getWidth(), hitBox.getY())) {
            return true;
        }
        return false;
    }
    public boolean checkCollisionBottom(HitBox hitBox) {
        if (gameMap.isSolidTile(hitBox.getX(), hitBox.getY() + hitBox.getHeight()) || gameMap.isSolidTile(hitBox.getX() + hitBox.getWidth(), hitBox.getY() + hitBox.getHeight())) {
            return true;
        }
        return false;
    }
    public boolean checkCollisionLeft(HitBox hitBox) {
        if (gameMap.isSolidTile(hitBox.getX(), hitBox.getY()) || gameMap.isSolidTile(hitBox.getX(), hitBox.getY() + hitBox.getHeight())) {
            return true;
        }
        return false;
    }
    public boolean checkCollisionRight(HitBox hitBox) {
        if (gameMap.isSolidTile(hitBox.getX() + hitBox.getWidth(), hitBox.getY()) || gameMap.isSolidTile(hitBox.getX() + hitBox.getWidth(), hitBox.getY() + hitBox.getHeight())) {
            return true;
        }
        return false;
    }
}

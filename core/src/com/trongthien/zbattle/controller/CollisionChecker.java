package com.trongthien.zbattle.controller;

import com.trongthien.zbattle.common.SharedContext;
import com.trongthien.zbattle.view.map.GameMap;
import com.trongthien.zbattle.controller.combat.hitbox.HitBox;

public class CollisionChecker {
    //Singleton
    private static CollisionChecker collisionChecker = new CollisionChecker();
    public GameMap gameMap;

    private CollisionChecker() {
        gameMap = SharedContext.getInstance().getCurrentGameMap();
    }

    public static CollisionChecker getInstance() {
        if (collisionChecker == null) {
            collisionChecker = new CollisionChecker();
        }
        return collisionChecker;
    }

    public boolean checkCollisionTop(HitBox hitBox) {
        return gameMap.isSolidTile(hitBox.getX(), hitBox.getY()) || gameMap.isSolidTile(hitBox.getX() + hitBox.getWidth(), hitBox.getY());
    }

    public boolean checkCollisionBottom(HitBox hitBox) {
        return gameMap.isSolidTile(hitBox.getX(), hitBox.getY() + hitBox.getHeight()) || gameMap.isSolidTile(hitBox.getX() + hitBox.getWidth(), hitBox.getY() + hitBox.getHeight());
    }

    public boolean checkCollisionLeft(HitBox hitBox) {
        return gameMap.isSolidTile(hitBox.getX(), hitBox.getY()) || gameMap.isSolidTile(hitBox.getX(), hitBox.getY() + hitBox.getHeight());
    }

    public boolean checkCollisionRight(HitBox hitBox) {
        return gameMap.isSolidTile(hitBox.getX() + hitBox.getWidth(), hitBox.getY()) || gameMap.isSolidTile(hitBox.getX() + hitBox.getWidth(), hitBox.getY() + hitBox.getHeight());
    }
}

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
        for (int i = hitBox.getX(); i <= hitBox.getX() + hitBox.getWidth(); i++) {
            if (gameMap.isSolidTile(i, hitBox.getY() + hitBox.getHeight())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkCollisionBottom(HitBox hitBox) {
        for (int i = hitBox.getX(); i <= hitBox.getX() + hitBox.getWidth(); i++) {
            if (gameMap.isSolidTile(i, hitBox.getY())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkCollisionLeft(HitBox hitBox) {
        for (int i = hitBox.getY(); i <= hitBox.getY() + hitBox.getHeight(); i++) {
            if (gameMap.isSolidTile(hitBox.getX(), i)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkCollisionRight(HitBox hitBox) {
        for (int i = hitBox.getY(); i <= hitBox.getY() + hitBox.getHeight(); i++) {
            if (gameMap.isSolidTile(hitBox.getX() + hitBox.getWidth(), i)) {
                return true;
            }
        }
        return false;
    }
}

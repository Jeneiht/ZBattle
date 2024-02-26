package com.trongthien.zBattle.component;

import com.trongthien.zBattle.GameMap.GameMap;
import com.trongthien.zBattle.character.Entity;

public class CollisionChecker {
    public GameMap gameMap;
    public CollisionChecker(GameMap gameMap) {
        this.gameMap = gameMap;
    }
    public boolean checkCollisionTop(Entity entity){
        if (gameMap.isSolidTile(entity.solidArea.x, entity.solidArea.y) || gameMap.isSolidTile(entity.solidArea.x + entity.solidArea.width, entity.solidArea.y)) {
            return true;
        }
        return false;
    }
    public boolean checkCollisionBottom(Entity entity){
        if (gameMap.isSolidTile(entity.solidArea.x, entity.solidArea.y + entity.solidArea.height) || gameMap.isSolidTile(entity.solidArea.x + entity.solidArea.width, entity.solidArea.y + entity.solidArea.height)) {
            return true;
        }
        return false;
    }
    public boolean checkCollisionLeft(Entity entity){
        if (gameMap.isSolidTile(entity.solidArea.x, entity.solidArea.y) || gameMap.isSolidTile(entity.solidArea.x, entity.solidArea.y + entity.solidArea.height)) {
            return true;
        }
        return false;
    }
    public boolean checkCollisionRight(Entity entity){
        if (gameMap.isSolidTile(entity.solidArea.x + entity.solidArea.width, entity.solidArea.y) || gameMap.isSolidTile(entity.solidArea.x + entity.solidArea.width, entity.solidArea.y + entity.solidArea.height)) {
            return true;
        }
        return false;
    }
}

package com.trongthien.zBattle.component;

import com.trongthien.zBattle.GameMap.World;
import com.trongthien.zBattle.character.Entity;

public class CollisionChecker {
    public World world;
    public CollisionChecker(World world) {
        this.world = world;
    }
    public boolean checkCollisionTop(Entity entity){
        if (world.isSolid(entity.solidArea.x, entity.solidArea.y) || world.isSolid(entity.solidArea.x + entity.solidArea.width, entity.solidArea.y)) {
            return true;
        }
        return false;
    }
    public boolean checkCollisionBottom(Entity entity){
        if (world.isSolid(entity.solidArea.x, entity.solidArea.y + entity.solidArea.height) || world.isSolid(entity.solidArea.x + entity.solidArea.width, entity.solidArea.y + entity.solidArea.height)) {
            return true;
        }
        return false;
    }
    public boolean checkCollisionLeft(Entity entity){
        if (world.isSolid(entity.solidArea.x, entity.solidArea.y) || world.isSolid(entity.solidArea.x, entity.solidArea.y + entity.solidArea.height)) {
            return true;
        }
        return false;
    }
    public boolean checkCollisionRight(Entity entity){
        if (world.isSolid(entity.solidArea.x + entity.solidArea.width, entity.solidArea.y) || world.isSolid(entity.solidArea.x + entity.solidArea.width, entity.solidArea.y + entity.solidArea.height)) {
            return true;
        }
        return false;
    }
}

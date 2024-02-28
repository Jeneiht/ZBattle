package com.trongthien.zBattle.character;

import com.trongthien.zBattle.GameMap.GameMap;

public class Hero extends Player {

    public Hero(GameMap gameMap) {
        super(gameMap);
    }

    @Override
    protected void setBodyHitBox() {
        bodyHitBox = new HitBox(24,32,16,16);
    }

    @Override
    protected void setHealth() {
        health = 100;
    }

    @Override
    protected void setPlayerTileSetPath() {
        playerTileSetPath = "/character/hero/hero_tiles.png";
    }

    @Override
    protected void setRunSpeed() {
        runSpeed = 4;
    }

    @Override
    protected void setWalkSpeed() {
        walkSpeed = 2;
    }

    @Override
    protected void setIdleSpeed() {
        idleSpeed = 0;
    }

    @Override
    protected void setWidth() {
        width = 64;
    }

    @Override
    protected void setHeight() {
        height = 64;
    }

}

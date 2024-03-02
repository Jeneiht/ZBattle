package com.trongthien.zBattle.character;

import com.trongthien.zBattle.GameMap.GameMap;

public class Hero extends Player {

    public Hero(GameMap gameMap) {
        super(gameMap);
    }

    @Override
    protected void setAttackAHitBox() {
        attackAHitBox.put(Direction.DOWN, new HitBox(0, 24, 64, 40));
        attackAHitBox.put(Direction.UP, new HitBox(0, 0, 64, 40));
        attackAHitBox.put(Direction.LEFT, new HitBox(0, 0, 40, 64));
        attackAHitBox.put(Direction.RIGHT, new HitBox(24, 0, 40, 64));
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

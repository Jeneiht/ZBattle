package com.trongthien.zBattle.character;

import Movement.NormalMovement;
import Movement.RandomMovement;
import com.trongthien.zBattle.GameMap.GameMap;

public class Scorpion1 extends Enemy {
    public Scorpion1(GameMap gameMap, int x, int y, int id) {
        super(gameMap, x, y, id);
    }

    @Override
    protected void setCurrentMovement() {
        currentMovement = new RandomMovement();
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
    protected void setHealth() {
        health = 100;
    }

    @Override
    protected void setEnemyTileSetPath() {
        enemyTileSetPath = "/character/hero/scorpian1_tiles.png";
    }

    @Override
    protected void setBodyHitBox() {
        bodyHitBox = new HitBox(0,0,32,32);
    }
    @Override
    protected void setWidth() {
        width = 32;
    }

    @Override
    protected void setHeight() {
        height = 32;
    }
}

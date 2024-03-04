package com.trongthien.zbattle.model;

import com.trongthien.zbattle.controller.combat.attack.ScorpionAttack;
import com.trongthien.zbattle.controller.combat.hitbox.HitBox;
import com.trongthien.zbattle.controller.movement.Direction;
import com.trongthien.zbattle.controller.movement.impl.BFSPathFindingMovement;
import com.trongthien.zbattle.controller.movement.impl.LocalPathFindingMovement;
import com.trongthien.zbattle.model.constant.EnemyState;

import java.util.Map;

public class Scorpion1 extends Enemy {
    public Scorpion1(int x, int y) {
        super(x, y);
    }

    @Override
    public HitBox getHitBox() {
        return new HitBox(x,y,5,5);
    }

    @Override
    protected void setCurrentMovement() {
        currentMovement = new BFSPathFindingMovement();
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
        enemyTileSetPath = "character/hero/scorpian1_tiles.png";
    }

    @Override
    protected void setWidth() {
        width = 32;
    }

    @Override
    protected void setHeight() {
        height = 32;
    }

    @Override
    protected void updateEnemyState() {
        enemyState = EnemyState.ATTACK;
    }

    @Override
    protected void setCurrentAttack(EnemyState enemyState) {
        currentAttack = new ScorpionAttack(this);
    }
    @Override
    protected void loadFrameInfo() {
        //init mapTileY
        mapTileY.put(Map.of(EnemyState.IDLE, Direction.DOWN), 3);
        mapTileY.put(Map.of(EnemyState.IDLE, Direction.RIGHT), 3);
        mapTileY.put(Map.of(EnemyState.IDLE, Direction.UP), 3);
        mapTileY.put(Map.of(EnemyState.IDLE, Direction.LEFT), 3);
        mapTileY.put(Map.of(EnemyState.WALK, Direction.DOWN), 1);
        mapTileY.put(Map.of(EnemyState.WALK, Direction.RIGHT), 1);
        mapTileY.put(Map.of(EnemyState.WALK, Direction.UP), 1);
        mapTileY.put(Map.of(EnemyState.WALK, Direction.LEFT), 1);
        mapTileY.put(Map.of(EnemyState.ATTACK, Direction.DOWN), 2);
        mapTileY.put(Map.of(EnemyState.ATTACK, Direction.RIGHT), 2);
        mapTileY.put(Map.of(EnemyState.ATTACK, Direction.UP), 2);
        mapTileY.put(Map.of(EnemyState.ATTACK, Direction.LEFT), 2);


        //init maxFrame
        maxFrame.put(EnemyState.IDLE, 3);
        maxFrame.put(EnemyState.WALK, 3);
        maxFrame.put(EnemyState.ATTACK, 3);
    }
}

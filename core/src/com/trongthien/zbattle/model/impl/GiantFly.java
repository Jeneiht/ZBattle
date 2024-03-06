package com.trongthien.zbattle.model.impl;

import com.trongthien.zbattle.common.SharedContext;
import com.trongthien.zbattle.controller.combat.attack.GiantFlyAttack;
import com.trongthien.zbattle.controller.combat.hitbox.HitBox;
import com.trongthien.zbattle.controller.movement.Direction;
import com.trongthien.zbattle.controller.movement.impl.BFSPathFindingMovement;
import com.trongthien.zbattle.controller.movement.impl.NoMovement;
import com.trongthien.zbattle.model.Enemy;
import com.trongthien.zbattle.model.constant.EnemyState;

import java.util.Map;

public class GiantFly extends Enemy {

    public GiantFly(int x, int y) {
        super(x, y);
    }

    @Override
    protected void setAggroRange() {
        aggroRange = 200;
    }

    @Override
    protected void setRunSpeed() {
        runSpeed = 2;
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
        enemyTileSetPath = "character/Enemy/GiantFly.png";
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
    protected void setAttackSpeed() {
        attackSpeed = 2;
    }

    @Override
    protected void updateEnemyState() {
        if(health<=0){
            if(enemyState!=EnemyState.DIE) {
                speed=0;
                enemyState = EnemyState.DIE;
            }
            else{
                dead = true;
            }
        }else {
            if(Math.abs(x- SharedContext.getInstance().getCurrentPlayer().getX())<aggroRange&&Math.abs(y-SharedContext.getInstance().getCurrentPlayer().getY())<aggroRange){
                enemyState = EnemyState.ATTACK;
            }
            else{
                enemyState = EnemyState.IDLE;
            }
        }
    }

    @Override
    protected void setCurrentAttack(EnemyState enemyState) {
        if(enemyState == EnemyState.ATTACK){
            currentAttack = new GiantFlyAttack(this);
        }
    }

    @Override
    protected void updateMovement() {
        if(enemyState == EnemyState.IDLE){
            currentMovement = NoMovement.getInstance();
        }
        else if(enemyState == EnemyState.ATTACK){
            currentMovement = BFSPathFindingMovement.getInstance();
        }
        else{
            currentMovement = BFSPathFindingMovement.getInstance();
        }
    }

    @Override
    protected void loadFrameInfo() {
        mapTileY.put(Map.of(EnemyState.IDLE, Direction.DOWN), 0);
        mapTileY.put(Map.of(EnemyState.IDLE, Direction.RIGHT), 0);
        mapTileY.put(Map.of(EnemyState.IDLE, Direction.UP), 0);
        mapTileY.put(Map.of(EnemyState.IDLE, Direction.LEFT), 1);
        mapTileY.put(Map.of(EnemyState.WALK, Direction.DOWN), 0);
        mapTileY.put(Map.of(EnemyState.WALK, Direction.RIGHT), 0);
        mapTileY.put(Map.of(EnemyState.WALK, Direction.UP), 0);
        mapTileY.put(Map.of(EnemyState.WALK, Direction.LEFT), 1);
        mapTileY.put(Map.of(EnemyState.ATTACK, Direction.DOWN), 4);
        mapTileY.put(Map.of(EnemyState.ATTACK, Direction.RIGHT), 4);
        mapTileY.put(Map.of(EnemyState.ATTACK, Direction.UP), 4);
        mapTileY.put(Map.of(EnemyState.ATTACK, Direction.LEFT), 5);
        mapTileY.put(Map.of(EnemyState.DIE, Direction.DOWN), 6);
        mapTileY.put(Map.of(EnemyState.DIE, Direction.RIGHT), 6);
        mapTileY.put(Map.of(EnemyState.DIE, Direction.UP), 6);
        mapTileY.put(Map.of(EnemyState.DIE, Direction.LEFT), 7);


        //init maxFrame
        maxFrame.put(EnemyState.IDLE, 4);
        maxFrame.put(EnemyState.WALK, 4);
        maxFrame.put(EnemyState.ATTACK, 4);
        maxFrame.put(EnemyState.DIE, 6);
    }

    @Override
    public HitBox getHitBox() {
        return new HitBox(x+12,y+12,10,6);
    }
}

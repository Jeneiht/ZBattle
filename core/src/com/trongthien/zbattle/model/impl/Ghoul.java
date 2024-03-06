package com.trongthien.zbattle.model.impl;

import com.trongthien.zbattle.common.SharedContext;
import com.trongthien.zbattle.controller.combat.attack.GhoulAttack;
import com.trongthien.zbattle.controller.combat.hitbox.HitBox;
import com.trongthien.zbattle.controller.movement.Direction;
import com.trongthien.zbattle.controller.movement.impl.BFSPathFindingMovement;
import com.trongthien.zbattle.controller.movement.impl.LeftRightMovement;
import com.trongthien.zbattle.controller.movement.impl.NoMovement;
import com.trongthien.zbattle.model.Enemy;
import com.trongthien.zbattle.model.constant.EnemyState;

import java.util.Map;

public class Ghoul extends Enemy {
    public Ghoul(int x, int y) {
        super(x, y);
    }

    @Override
    protected void setAggroRange() {
        aggroRange = 50;
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
        health = 200;
    }

    @Override
    protected void setEnemyTileSetPath() {
        enemyTileSetPath = "character/Enemy/Ghoul.png";
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
            if(enemyState!= EnemyState.DIE) {
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
                enemyState = EnemyState.WALK;
            }
        }
    }

    @Override
    protected void setCurrentAttack(EnemyState enemyState) {
        if(enemyState == EnemyState.ATTACK){
            currentAttack = new GhoulAttack(this);
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
            currentMovement = LeftRightMovement.getInstance();
        }
    }

    @Override
    protected void loadFrameInfo() {
        mapTileY.put(Map.of(EnemyState.IDLE, Direction.DOWN), 0);
        mapTileY.put(Map.of(EnemyState.IDLE, Direction.RIGHT), 0);
        mapTileY.put(Map.of(EnemyState.IDLE, Direction.UP), 0);
        mapTileY.put(Map.of(EnemyState.IDLE, Direction.LEFT), 1);
        mapTileY.put(Map.of(EnemyState.WALK, Direction.DOWN), 2);
        mapTileY.put(Map.of(EnemyState.WALK, Direction.RIGHT), 2);
        mapTileY.put(Map.of(EnemyState.WALK, Direction.UP), 2);
        mapTileY.put(Map.of(EnemyState.WALK, Direction.LEFT), 3);
        mapTileY.put(Map.of(EnemyState.ATTACK, Direction.DOWN), 4);
        mapTileY.put(Map.of(EnemyState.ATTACK, Direction.RIGHT), 4);
        mapTileY.put(Map.of(EnemyState.ATTACK, Direction.UP), 4);
        mapTileY.put(Map.of(EnemyState.ATTACK, Direction.LEFT), 5);
        mapTileY.put(Map.of(EnemyState.DIE, Direction.DOWN), 8);
        mapTileY.put(Map.of(EnemyState.DIE, Direction.RIGHT), 8);
        mapTileY.put(Map.of(EnemyState.DIE, Direction.UP), 8);
        mapTileY.put(Map.of(EnemyState.DIE, Direction.LEFT), 9);


        //init maxFrame
        maxFrame.put(EnemyState.IDLE, 4);
        maxFrame.put(EnemyState.WALK, 8);
        maxFrame.put(EnemyState.ATTACK, 6);
        maxFrame.put(EnemyState.DIE, 6);
    }

    @Override
    public HitBox getHitBox() {
        return new HitBox(x+12,y+12,10,6);
    }
}

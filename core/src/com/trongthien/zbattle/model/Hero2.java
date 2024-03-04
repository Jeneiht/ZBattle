package com.trongthien.zbattle.model;

import com.trongthien.zbattle.controller.combat.attack.NormalSword;
import com.trongthien.zbattle.controller.combat.hitbox.HitBox;
import com.trongthien.zbattle.controller.movement.Direction;
import com.trongthien.zbattle.model.constant.PlayerState;

import java.util.Map;

public class Hero2 extends Player  {
    public Hero2() {
        super();
    }
    @Override
    public HitBox getHitBox() {
        return new HitBox(x+10,y+1,9,9);
    }

    @Override
    protected void setHealth() {
        health = 100;
    }

    @Override
    protected void setPlayerTileSetPath() {
        playerTileSetPath = "character/hero/hero2.png";
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
        width = 32;
    }

    @Override
    protected void setHeight() {
        height = 32;
    }

    @Override
    protected void setCurrentAttack(PlayerState playerState) {
        switch (playerState) {
            case ATTACKA:
                currentAttack = new NormalSword(this);
                break;
        }
    }

    @Override
    protected void loadFrameInfo() {
        //init mapTileY
        mapTileY.put(Map.of(PlayerState.IDLE, Direction.DOWN), 0);
        mapTileY.put(Map.of(PlayerState.IDLE, Direction.LEFT), 0);
        mapTileY.put(Map.of(PlayerState.IDLE, Direction.RIGHT), 0);
        mapTileY.put(Map.of(PlayerState.IDLE, Direction.UP), 0);
        mapTileY.put(Map.of(PlayerState.WALK, Direction.DOWN), 1);
        mapTileY.put(Map.of(PlayerState.WALK, Direction.LEFT), 1);
        mapTileY.put(Map.of(PlayerState.WALK, Direction.RIGHT), 1);
        mapTileY.put(Map.of(PlayerState.WALK, Direction.UP), 1);
        mapTileY.put(Map.of(PlayerState.RUN, Direction.DOWN), 1);
        mapTileY.put(Map.of(PlayerState.RUN, Direction.LEFT), 1);
        mapTileY.put(Map.of(PlayerState.RUN, Direction.RIGHT), 1);
        mapTileY.put(Map.of(PlayerState.RUN, Direction.UP), 1);
        mapTileY.put(Map.of(PlayerState.ATTACKA, Direction.DOWN), 2);
        mapTileY.put(Map.of(PlayerState.ATTACKA, Direction.RIGHT), 2);
        mapTileY.put(Map.of(PlayerState.ATTACKA, Direction.UP), 2);
        mapTileY.put(Map.of(PlayerState.ATTACKA, Direction.LEFT), 2);

        //init maxFrame
        maxFrame.put(PlayerState.IDLE, 13);
        maxFrame.put(PlayerState.WALK, 8);
        maxFrame.put(PlayerState.RUN, 8);
        maxFrame.put(PlayerState.ATTACKA, 10);

    }
}

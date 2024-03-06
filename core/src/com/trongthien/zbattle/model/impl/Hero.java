package com.trongthien.zbattle.model.impl;

import com.trongthien.zbattle.controller.combat.attack.NormalSword;
import com.trongthien.zbattle.controller.combat.hitbox.HitBox;
import com.trongthien.zbattle.controller.movement.Direction;
import com.trongthien.zbattle.model.Player;
import com.trongthien.zbattle.model.constant.PlayerState;

import java.util.Map;

public class Hero extends Player {

    public Hero() {
        super();
    }

    private static final int RUN_SPEED = 4;
    private static final int WALK_SPEED = 2;
    private static final int IDLE_SPEED = 0;
    private static final int MAX_HEALTH = 100;


    @Override
    public HitBox getHitBox() {
        return new HitBox(x + 24, y + 32, 16, 16);
    }

    @Override
    protected void setHealth() {
        health = MAX_HEALTH;
    }

    @Override
    protected void setPlayerTileSetPath() {
        playerTileSetPath = "character/hero/hero_tiles.png";
    }

    @Override
    protected void setRunSpeed() {
        runSpeed = RUN_SPEED;
    }

    @Override
    protected void setWalkSpeed() {
        walkSpeed = WALK_SPEED;
    }

    @Override
    protected void setIdleSpeed() {
        idleSpeed = IDLE_SPEED;
    }

    @Override
    protected void setWidth() {
        width = 64;
    }

    @Override
    protected void setHeight() {
        height = 64;
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
        mapTileY.put(Map.of(PlayerState.IDLE, Direction.RIGHT), 1);
        mapTileY.put(Map.of(PlayerState.IDLE, Direction.UP), 2);
        mapTileY.put(Map.of(PlayerState.IDLE, Direction.LEFT), 3);
        mapTileY.put(Map.of(PlayerState.WALK, Direction.DOWN), 4);
        mapTileY.put(Map.of(PlayerState.WALK, Direction.RIGHT), 5);
        mapTileY.put(Map.of(PlayerState.WALK, Direction.UP), 6);
        mapTileY.put(Map.of(PlayerState.WALK, Direction.LEFT), 7);
        mapTileY.put(Map.of(PlayerState.RUN, Direction.DOWN), 8);
        mapTileY.put(Map.of(PlayerState.RUN, Direction.RIGHT), 9);
        mapTileY.put(Map.of(PlayerState.RUN, Direction.UP), 10);
        mapTileY.put(Map.of(PlayerState.RUN, Direction.LEFT), 11);
        mapTileY.put(Map.of(PlayerState.ATTACKA, Direction.DOWN), 12);
        mapTileY.put(Map.of(PlayerState.ATTACKA, Direction.RIGHT), 13);
        mapTileY.put(Map.of(PlayerState.ATTACKA, Direction.UP), 14);
        mapTileY.put(Map.of(PlayerState.ATTACKA, Direction.LEFT), 15);


        //init maxFrame
        maxFrame.put(PlayerState.IDLE, 8);
        maxFrame.put(PlayerState.WALK, 8);
        maxFrame.put(PlayerState.RUN, 8);
        maxFrame.put(PlayerState.ATTACKA, 6);
    }

}

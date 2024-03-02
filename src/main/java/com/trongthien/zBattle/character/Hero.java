package com.trongthien.zBattle.character;

import com.trongthien.zBattle.Attack.NormalSword;
import com.trongthien.zBattle.GameMap.GameMap;

import java.util.Map;

public class Hero extends Player {

    public Hero() {
        super();
    }

    @Override
    public HitBox getHitBox() {
        return new HitBox(x+24,y+32,16,16);
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

    @Override
    protected void setCurrentAttack(PlayerState playerState) {
        switch (playerState){
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

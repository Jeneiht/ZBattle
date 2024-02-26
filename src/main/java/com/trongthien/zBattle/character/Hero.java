package com.trongthien.zBattle.character;

import com.trongthien.zBattle.GameMap.GameMap;
import com.trongthien.zBattle.GameMap.TileSet;
import com.trongthien.zBattle.component.AnimationUtils;
import com.trongthien.zBattle.component.CollisionChecker;
import com.trongthien.zBattle.constant.GameConstant;

public class Hero extends Player {
    public Hero(GameMap gameMap) {
        this.gameMap = gameMap;
        collisionChecker = new CollisionChecker(gameMap);
        this.x = gameMap.getSpawnX();
        this.y = gameMap.getSpawnY();
        playerTileSetPath = "/character/hero/hero_tiles.png";
        width = 64;
        height = 64;
        speed = 3;
        solidX = 24;
        solidY = 32;
        solidWidth = 16;
        solidHeight = 16;
        playerState = PlayerState.IDLE;
        playerTileSet = new TileSet(playerTileSetPath,width);
        direction = Direction.DOWN;
        load();
    }
}

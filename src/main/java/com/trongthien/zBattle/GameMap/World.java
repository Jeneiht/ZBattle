package com.trongthien.zBattle.GameMap;


import com.trongthien.zBattle.character.Enemy;
import com.trongthien.zBattle.character.Scorpion1;
import com.trongthien.zBattle.component.SharedCurrentContext;

import java.awt.*;

public class World extends GameMap {
    public World() {
        super();
    }

    @Override
    protected void setMaxCol() {
        maxCol = 60;
    }

    @Override
    protected void setMaxRow() {
        maxRow = 60;
    }

    @Override
    protected void setTileSize() {
        tileSize = 32;
    }

    @Override
    protected void setGameMapPath() {
        gameMapPath = "/map/world.txt";
    }

    @Override
    protected void setSolidTilesPath() {
        solidTilesPath = "/map/solid_tiles.txt";
    }

    @Override
    protected void setTileSetPath() {
        tileSetPath = "/map/world_tiles.png";
    }

    @Override
    protected void setSpawnX() {
        spawnX = 50;
    }

    @Override
    protected void setSpawnY() {
        spawnY = 50;
    }

    @Override
    public void loadEntities() {
        addEntity(new Scorpion1(100, 100));
        addEntity(new Scorpion1(100, 200));
        addEntity(new Scorpion1(100, 300));
    }

}

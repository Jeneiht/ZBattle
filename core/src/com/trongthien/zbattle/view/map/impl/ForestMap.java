package com.trongthien.zbattle.view.map.impl;


import com.trongthien.zbattle.common.constant.GameConstant;
import com.trongthien.zbattle.model.Scorpion1;
import com.trongthien.zbattle.view.map.GameMap;

public class ForestMap extends GameMap {

    private static final int SPAWN_X = 304;
    private static final int SPAWN_Y = 116;

    public ForestMap() {
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
        tileSize = GameConstant.tileSize;
    }

    @Override
    protected void setGameMapPath() {
        gameMapPath = "map/lmao.xml";
    }

    @Override
    protected void setSolidTilesPath() {
        solidTilesPath = "map/solid_tiles.txt";
    }

    @Override
    protected void setTileSetPath() {
        tileSetPath = "map/lmaoTileSet.png";
    }

    @Override
    protected void setSpawnX() {
        spawnX = SPAWN_X;
    }

    @Override
    protected void setSpawnY() {
        spawnY = SPAWN_Y;
    }

    @Override
    public void loadEntities() {
        addEntity(new Scorpion1(100, 300));
        addEntity(new Scorpion1(100, 400));
        addEntity(new Scorpion1(100, 500));
    }

}

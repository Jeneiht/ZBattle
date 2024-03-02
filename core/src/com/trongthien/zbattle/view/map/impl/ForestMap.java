package com.trongthien.zbattle.view.map.impl;


import com.trongthien.zbattle.view.map.GameMap;

public class ForestMap extends GameMap {
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
        tileSize = 16;
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
        spawnX = 50;
    }

    @Override
    protected void setSpawnY() {
        spawnY = 50;
    }

    @Override
    public void loadEntities() {
//        addEntity(new Scorpion1(100, 100));
//        addEntity(new Scorpion1(100, 200));
//        addEntity(new Scorpion1(100, 300));
    }

}

package com.trongthien.zbattle.view.map.impl;


import com.trongthien.zbattle.common.constant.GameConstant;
import com.trongthien.zbattle.model.impl.Ghoul;
import com.trongthien.zbattle.model.impl.GiantFly;
import com.trongthien.zbattle.model.impl.Ratfolk;
import com.trongthien.zbattle.view.map.GameMap;

public class ForestMap extends GameMap {

    private static final int SPAWN_X = 322;
    private static final int SPAWN_Y = 793;

    public ForestMap() {
        super();
    }

    @Override
    protected void setCoverTilesPath() {
        coverTilesPath = "map/lmaoCoverTiles.xml";
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
           addEntity(new GiantFly(100, 50));
           addEntity(new Ratfolk(200, 50));
           addEntity(new Ghoul(300,800));
    }

}

package com.trongthien.zBattle.GameMap;


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
        tileSize = 16;
    }

    @Override
    protected void setGameMapPath() {
        gameMapPath = "src/main/resources/map/lmao.xml";
    }

    @Override
    protected void setSolidTilesPath() {
        solidTilesPath = "/map/solid_tiles.txt";
    }

    @Override
    protected void setTileSetPath() {
        tileSetPath = "/map/lmaoTileSet.png";
    }

    @Override
    protected void setSpawnX() {
        spawnX = 304;
    }

    @Override
    protected void setSpawnY() {
        spawnY = 116;
    }

    @Override
    public void loadEntities() {
//        addEntity(new Scorpion1(100, 100));
//        addEntity(new Scorpion1(100, 200));
//        addEntity(new Scorpion1(100, 300));
    }

}

package com.trongthien.zBattle.GameMap;


public class World extends GameMap {
    public World() {
        maxCol = 60;
        maxRow = 60;
        tileSize = 32;
        width = maxCol * tileSize;
        height = maxRow * tileSize;
        tiles = new Tile[maxRow][maxCol];
        tileSetPath = "/map/world_tiles.png";
        gameMapPath = "/map/world.txt";
        solidTilesPath = "/map/solid_tiles.txt";
        tileSet = new TileSet(tileSetPath,tileSize);
        spawnX = 50;
        spawnY = 50;
        load();
    }
}

package com.trongthien.zBattle.GameMap;


import com.trongthien.zBattle.character.Enemy;
import com.trongthien.zBattle.character.Scorpion1;

import java.awt.*;

public class World extends GameMap {
    public World() {
        super();
    }

    @Override
    protected void loadEnemies() {
        for(int i = 0; i < 20; i++) {
            Enemy enemy = new Scorpion1(this, 50 + i * 50, 50 + i * 50, i);
            enemies.add(enemy);
        }
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

}

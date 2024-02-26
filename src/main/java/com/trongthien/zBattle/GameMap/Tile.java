package com.trongthien.zBattle.GameMap;

import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;

public class Tile {
    @Getter
    @Setter
    private BufferedImage image;
    @Getter
    @Setter
    public boolean solid=false;
    public Tile(TileSet tileSet, int x, int y,int tileWidth, int tileHeight) {
        image =tileSet.getImage().getSubimage(x*tileWidth, y*tileHeight, tileWidth, tileHeight);
    }
    public boolean isSolid() {
        return solid;
    }

}

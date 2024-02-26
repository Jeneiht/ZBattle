package com.trongthien.zBattle.GameMap;

import java.awt.image.BufferedImage;

public class Tile {
    public BufferedImage image;
    boolean solid=false;
    public Tile(TileSet tileSet, int x, int y,int tileWidth, int tileHeight) {
        image =tileSet.getImage().getSubimage(x*tileWidth, y*tileHeight, tileWidth, tileHeight);
    }
    public BufferedImage getImage() {
        return image;
    }
    public void setSolid(){
        solid=true;
    }
    public boolean isSolid() {
        return solid;
    }

}

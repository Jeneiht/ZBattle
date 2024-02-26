package com.trongthien.zBattle.GameMap;

import com.trongthien.zBattle.component.ResourceLoader;

import java.awt.image.BufferedImage;

public class TileSet {
    private BufferedImage image;
    public TileSet(String tileSetPath) {
        image = ResourceLoader.getInstance().loadImage(tileSetPath);
    }
    public BufferedImage getImage() {
        return image;
    }
}

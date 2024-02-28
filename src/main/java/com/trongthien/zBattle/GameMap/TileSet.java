package com.trongthien.zBattle.GameMap;

import com.trongthien.zBattle.component.ResourceLoader;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;

public class TileSet {
    @Getter
    private BufferedImage image;
    @Getter
    @Setter
    public int maxCol;
    @Getter
    @Setter
    public int maxRow;
    public int tileSize;
    public TileSet(String path,int tileSize) {
        this.tileSize = tileSize;
        image = ResourceLoader.getInstance().loadImage(path);
        maxCol = image.getWidth()/tileSize;
        maxRow = image.getHeight()/tileSize;

    }
    public BufferedImage getImage() {
        return image;
    }
}

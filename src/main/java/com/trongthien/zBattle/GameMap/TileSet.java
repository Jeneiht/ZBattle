package com.trongthien.zBattle.GameMap;

import com.trongthien.zBattle.component.ResourceLoader;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;

@Getter
@Setter
public class TileSet {
    private BufferedImage image;
    public int maxCol;
    public int maxRow;
    public int tileSize;

    public TileSet(String path, int tileSize) {
        this.tileSize = tileSize;
        image = ResourceLoader.getInstance().loadImage(path);
        maxCol = image.getWidth() / tileSize;
        maxRow = image.getHeight() / tileSize;

    }

    public BufferedImage getImage() {
        return image;
    }
}

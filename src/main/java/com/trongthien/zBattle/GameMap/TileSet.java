package com.trongthien.zBattle.GameMap;

import com.trongthien.zBattle.component.ResourceLoader;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class TileSet {
    private static Map<String, BufferedImage> imageCache = new HashMap<>();
    private BufferedImage image;
    public int maxCol;
    public int maxRow;
    public int tileSize;

    public TileSet(String path, int tileSize) {
        this.tileSize = tileSize;
        image = loadImage(path);
        maxCol = image.getWidth() / tileSize;
        maxRow = image.getHeight() / tileSize;

    }

    private BufferedImage loadImage(String path) {
        if (!imageCache.containsKey(path)) {
            imageCache.put(path, ResourceLoader.getInstance().loadImage(path));
        }
        return imageCache.get(path);
    }
}

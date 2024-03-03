package com.trongthien.zbattle.view.map;

import com.badlogic.gdx.graphics.Texture;
import com.trongthien.zbattle.common.io.ResourceLoader;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class TileSet {
    private static Map<String, Texture> imageCache = new HashMap<>();
    private Texture texture;
    public int maxCol;
    public int maxRow;
    public int tileSize;

    public TileSet(String path, int tileSize) {
        this.tileSize = tileSize;
        texture = loadImage(path);
        maxCol = texture.getWidth() / tileSize;
        maxRow = texture.getHeight() / tileSize;

    }

    private Texture loadImage(String path) {
        if (!imageCache.containsKey(path)) {
            imageCache.put(path, ResourceLoader.getInstance().loadImage(path));
        }
        return imageCache.get(path);
    }
}

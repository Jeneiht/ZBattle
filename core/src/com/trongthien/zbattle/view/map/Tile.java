package com.trongthien.zbattle.view.map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Tile {
    private static Map<TileSet, Map<Point, Tile>> tileCache = new HashMap<>();
    private TextureRegion textureRegion;

    public Tile(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    public Tile(TileSet tileSet, int x, int y, int tileWidth, int tileHeight) {
        //this.textureRegion.flip(false, true);
        this.textureRegion = getCacheImage(tileSet, x, y, tileWidth, tileHeight).getTextureRegion();
    }

    public static Tile getCacheImage(TileSet tileSet, int x, int y, int tileWidth, int tileHeight) {
        return tileCache.computeIfAbsent(tileSet, k -> new HashMap<>())
                .computeIfAbsent(new Point(x, y), k -> new Tile(new TextureRegion(tileSet.getTexture(), x * tileWidth, y * tileHeight, tileWidth, tileHeight)));
    }
}

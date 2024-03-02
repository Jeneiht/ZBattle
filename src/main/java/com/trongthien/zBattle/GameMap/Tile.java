package com.trongthien.zBattle.GameMap;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Tile {
    private static Map<TileSet, Map<Point, Tile>> tileCache = new HashMap<>();
    private BufferedImage image;
    public boolean solid = false;

    public Tile(BufferedImage image) {
        this.image = image;
    }

    public Tile(TileSet tileSet, int x, int y, int tileWidth, int tileHeight) {
        //this.image = tileSet.getImage().getSubimage(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
        this.image = getCacheImage(tileSet, x, y, tileWidth, tileHeight).getImage();
    }

    public static Tile getCacheImage(TileSet tileSet, int x, int y, int tileWidth, int tileHeight) {
        return tileCache.computeIfAbsent(tileSet, k -> new HashMap<>())
                .computeIfAbsent(new Point(x, y), k -> new Tile(tileSet.getImage().getSubimage(x * tileWidth, y * tileHeight, tileWidth, tileHeight)));
    }
}

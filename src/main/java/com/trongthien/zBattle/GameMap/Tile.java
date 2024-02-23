package com.trongthien.zBattle.GameMap;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Tile {
    BufferedImage image;
    public Tile(int num) {
        int x=num/40 +1;
        int y=num%40 +1;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource("src/main/resources/map/world_tiles.png"))).getSubimage(x*32, y*32, 32, 32);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

package com.trongthien.zBattle.GameMap;

import java.awt.*;

public interface Map {
    void loadMap();
    void draw(Graphics2D g2d,Camera camera);
    boolean isSolid(int x, int y);
}

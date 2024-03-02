package com.trongthien.zbattle.common.constant;

public class GameConstant {
    public static final int originalTileSize = 16;
    public static final int scale = 1;
    public static final int minSpeed = 1;
    public static final int tileSize = originalTileSize * scale;
    public static final int maxScreenCol = 768 / tileSize;
    public static final int maxScreenRow = 576 / tileSize;

    public static final int screenWidth = tileSize * maxScreenCol;
    public static final int screenHeight = tileSize * maxScreenRow;
    public static final int FPS = 60;
    public static final int animationSpeed = 4;

}

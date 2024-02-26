package com.trongthien.zBattle.GameMap;

import com.trongthien.zBattle.component.ResourceLoader;
import com.trongthien.zBattle.constant.GameConstant;

import java.awt.*;
import java.util.Scanner;
import java.util.Set;


public class World implements Map {
    public final int maxWorldCol = 60;
    public final int maxWorldRow = 60;

    public int spawnX = 50;
    public int spawnY = 50;
    public final int worldTileSize = 32;
    public final int worldWidth = maxWorldCol * worldTileSize;
    public final int worldHeight = maxWorldRow * worldTileSize;
    public final int worldTileSetMaxCol = 40;
    public final int worldTileSetMaxRow = 40;
    public Tile[][] tiles;
    public String worldTileSetPath = "/map/world_tiles.png";
    public String worldPath = "/map/world.txt";
    public String worldSolidTileSetPath = "/map/solid_tiles.txt";

    public TileSet worldTileSet=new TileSet(worldTileSetPath);
    Set<Integer> solidTiles = new java.util.HashSet<>();

    public World() {
        tiles = new Tile[maxWorldRow][maxWorldCol];
        loadSolidTiles();
    }

    @Override
    public void loadMap() {
        Scanner scanner;
        scanner = new Scanner(ResourceLoader.getInstance().loadInputStream(worldPath));
        for (int row = 0; row < maxWorldRow; row++) {
            String line = scanner.nextLine();
            for (int col = 0; col < maxWorldCol; col++) {
                tiles[row][col] = getTile(Integer.parseInt(line.split(",")[col]));
                if (solidTiles.contains(Integer.parseInt(line.split(",")[col]))) {
                    tiles[row][col].setSolid();
                }
            }
        }
    }

    private void loadSolidTiles() {
        Scanner scanner;
        scanner = new Scanner(ResourceLoader.getInstance().loadInputStream(worldSolidTileSetPath));
        String line = scanner.nextLine();
        for (String num : line.split(",")) {
            solidTiles.add(Integer.parseInt(num));
        }
    }

    @Override
    public boolean isSolid(int x, int y) {
        int row = y / worldTileSize;
        int col = x / worldTileSize;
        return tiles[row][col].isSolid();
    }

    private Tile getTile(int num) {
        int x = num % worldTileSetMaxCol;
        int y = num / worldTileSetMaxRow;
        return new Tile(worldTileSet, x, y, worldTileSize, worldTileSize);
    }
    @Override
    public void draw(Graphics2D g2d, Camera camera) {
        int startRow = camera.getY() / worldTileSize;
        int startCol = camera.getX() / worldTileSize;
        for (int i = startRow; i <= startRow + GameConstant.maxScreenRow && i < maxWorldRow; i++) {
            for (int j = startCol; j <= startCol + GameConstant.maxScreenCol && j < maxWorldCol; j++) {
                g2d.drawImage(tiles[i][j].getImage(), j * worldTileSize - camera.getX(), i * worldTileSize - camera.getY(), null);
            }
        }
    }
}

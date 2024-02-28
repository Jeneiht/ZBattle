package com.trongthien.zBattle.GameMap;

import com.trongthien.zBattle.component.ResourceLoader;
import com.trongthien.zBattle.constant.GameConstant;
import lombok.Getter;

import java.awt.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GameMap {
    public int maxCol;
    public int maxRow;
    public int tileSize;
    public int width;
    public int height;
    public Tile[][] tiles;

    public String tileSetPath;
    public String gameMapPath;
    public String solidTilesPath;
    public TileSet tileSet;
    @Getter
    public int spawnX;
    @Getter
    public int spawnY;

    protected void load() {
        Scanner scanner = new Scanner(ResourceLoader.getInstance().loadInputStream(gameMapPath));
        for (int row = 0; row < maxRow; row++) {
            String line = scanner.nextLine();
            for (int col = 0; col < maxCol; col++) {
                tiles[row][col] = getTile(Integer.parseInt(line.split(",")[col]));
            }
        }
        scanner.close();
        loadSolidTiles();
    }
    private void loadSolidTiles() {
        Set<Integer> solidTiles = new HashSet<>();
        Scanner scanner = new Scanner(ResourceLoader.getInstance().loadInputStream(solidTilesPath));
        String line = scanner.nextLine();
        for (String num : line.split(",")) {
            solidTiles.add(Integer.parseInt(num));
        }
        scanner = new Scanner(ResourceLoader.getInstance().loadInputStream(gameMapPath));
        for (int row = 0; row < maxRow; row++) {
            line = scanner.nextLine();
            for (int col = 0; col < maxCol; col++) {
                if (solidTiles.contains(Integer.parseInt(line.split(",")[col]))) {
                    tiles[row][col].setSolid(true);
                }
            }
        }
        scanner.close();
    }
    private Tile getTile(int id) {
        int x = id % tileSet.getMaxCol();
        int y = id / tileSet.getMaxRow();
        return new Tile(tileSet, x, y, tileSize, tileSize);
    }

    public boolean isSolidTile(int x, int y){
        int row = y/tileSize;
        int col = x/tileSize;
        return tiles[row][col].isSolid();
    }
    public void draw(Graphics2D g2d, Camera camera){
        int startRow = camera.getY()/tileSize;
        int startCol = camera.getX()/tileSize;
        for (int i = startRow; i <= startRow + GameConstant.maxScreenRow && i < maxRow; i++) {
            for (int j = startCol; j <= startCol + GameConstant.maxScreenCol && j < maxCol; j++) {
                g2d.drawImage(tiles[i][j].getImage(), j*tileSize - camera.getX(), i*tileSize - camera.getY(), null);
            }
        }
    }
}

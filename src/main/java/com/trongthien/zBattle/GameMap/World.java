package com.trongthien.zBattle.GameMap;

import com.trongthien.zBattle.constant.GameConstant;
import com.trongthien.zBattle.screen.MainPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;


public class World {
    public final int maxWorldCol = 60;
    public final int maxWorldRow = 60;
    MainPanel mainPanel;
    public final int worldWidth = maxWorldCol * GameConstant.tileSize;
    public final int worldHeight = maxWorldRow * GameConstant.tileSize;
    public int[][] tiles;
    public BufferedImage worldTiles;
    Set<Integer> solidTiles = new java.util.HashSet<>();

    public World(MainPanel mainPanel) throws IOException {
        this.mainPanel = mainPanel;
        tiles = new int[maxWorldRow][maxWorldCol];
        worldTiles = ImageIO.read(new File("src/main/resources/map/world_tiles.png"));

        loadSolidTiles();
    }

    public void loadWorld() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/main/resources/map/world.txt"));
        for (int row = 0; row < maxWorldRow; row++) {
            String line = scanner.nextLine();
            for (int col = 0; col < maxWorldCol; col++) {
                tiles[row][col] = Integer.parseInt(line.split(",")[col]);
            }
        }
    }

    private void loadSolidTiles() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/main/resources/map/solid_tiles.txt"));
        String line = scanner.nextLine();
        for (String num : line.split(",")) {
            solidTiles.add(Integer.parseInt(num));
        }
    }

    public boolean isSolid(int x, int y) {
        int row = y / GameConstant.tileSize;
        int col = x / GameConstant.tileSize;
        return solidTiles.contains(tiles[row][col]);
    }

    private BufferedImage getTile(int num) {
        int x = num % 40;
        int y = num / 40;
        return worldTiles.getSubimage(x * GameConstant.tileSize, y * GameConstant.tileSize, GameConstant.tileSize, GameConstant.tileSize);
    }

    public void draw(Graphics2D g2d, int cameraX, int cameraY) throws IOException {
        int startRow = cameraY / GameConstant.tileSize;
        int startCol = cameraX / GameConstant.tileSize;
        for (int i = startRow; i <= startRow + GameConstant.maxScreenRow && i < maxWorldRow; i++) {
            for (int j = startCol; j <= startCol + GameConstant.maxScreenCol && j < maxWorldCol; j++) {
                g2d.drawImage(getTile(tiles[i][j]), j * GameConstant.tileSize - cameraX, i * GameConstant.tileSize - cameraY, null);

            }
        }
    }
}

package com.trongthien.zBattle.GameMap;

import com.trongthien.zBattle.character.Enemy;
import com.trongthien.zBattle.component.ResourceLoader;
import com.trongthien.zBattle.constant.GameConstant;
import lombok.Getter;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

@Getter
public abstract class GameMap {
    protected int maxCol;
    protected int maxRow;
    protected int tileSize;
    protected int width;
    protected int height;
    protected Tile[][] tiles;

    protected String tileSetPath;
    protected String gameMapPath;
    protected String solidTilesPath;
    protected TileSet tileSet;
    protected int spawnX;
    protected int spawnY;
    protected ArrayList<Enemy> enemies = new ArrayList<>();

    protected GameMap() {
        setGameMapPath();
        setTileSetPath();
        setSolidTilesPath();
        setMaxCol();
        setMaxRow();
        setTileSize();
        setSpawnX();
        setSpawnY();
        width = maxCol * tileSize;
        height = maxRow * tileSize;
        tiles = new Tile[maxRow][maxCol];
        tileSet = new TileSet(tileSetPath, tileSize);
        load();
        loadEnemies();
    }
    protected abstract void loadEnemies();
    protected abstract void setMaxCol();
    protected abstract void setMaxRow();
    protected abstract void setTileSize();
    protected abstract void setGameMapPath();
    protected abstract void setSolidTilesPath();
    protected abstract void setTileSetPath();
    protected abstract void setSpawnX();
    protected abstract void setSpawnY();
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
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }
    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
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
    public void update() {
        for (Enemy enemy : enemies) {
            enemy.update();
        }
    }
    public boolean isSolidTile(int x, int y){
        int row = y/tileSize;
        int col = x/tileSize;
        return tiles[row][col].isSolid();
    }
    private void drawEnemies(Graphics2D g2d, Camera camera) {
        for (Enemy enemy : enemies) {
            if(enemy.getHealth() >0) {
                enemy.draw(g2d, camera);
            }
        }
    }
    public void draw(Graphics2D g2d, Camera camera){
        int startRow = camera.getY()/tileSize;
        int startCol = camera.getX()/tileSize;
        for (int i = startRow; i <= startRow + GameConstant.maxScreenRow && i < maxRow; i++) {
            for (int j = startCol; j <= startCol + GameConstant.maxScreenCol && j < maxCol; j++) {
                g2d.drawImage(tiles[i][j].getImage(), j*tileSize - camera.getX(), i*tileSize - camera.getY(), null);
            }
        }
        drawEnemies(g2d, camera);
    }
}

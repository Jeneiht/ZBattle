package com.trongthien.zBattle.GameMap;

import com.trongthien.zBattle.Attack.Attack;
import com.trongthien.zBattle.character.Enemy;
import com.trongthien.zBattle.character.Entity;
import com.trongthien.zBattle.character.HitBox;
import com.trongthien.zBattle.character.Scorpion1;
import com.trongthien.zBattle.component.HitBoxUtils;
import com.trongthien.zBattle.component.ResourceLoader;
import com.trongthien.zBattle.component.SharedCurrentContext;
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
    protected ArrayList<Entity> entities;
    protected ArrayList<Attack> attacks;


    public GameMap() {
        entities = new ArrayList<>();
        attacks = new ArrayList<>();
        addEntity(SharedCurrentContext.getInstance().getCurrentPlayer());
        setGameMapPath();
        setTileSetPath();
        setSolidTilesPath();
        setMaxCol();
        setMaxRow();
        setTileSize();
        setSpawnX();
        setSpawnY();
        spawnPlayer();
        width = maxCol * tileSize;
        height = maxRow * tileSize;
        tiles = new Tile[maxRow][maxCol];
        tileSet = new TileSet(tileSetPath, tileSize);
        load();
        loadEntities();
    }

    protected abstract void setMaxCol();

    protected abstract void setMaxRow();

    protected abstract void setTileSize();

    protected abstract void setGameMapPath();

    protected abstract void setSolidTilesPath();

    protected abstract void setTileSetPath();

    protected abstract void setSpawnX();

    protected abstract void setSpawnY();

    public abstract void loadEntities();

    private void spawnPlayer() {
        SharedCurrentContext.getInstance().getCurrentPlayer().setX(spawnX);
        SharedCurrentContext.getInstance().getCurrentPlayer().setY(spawnY);
    }

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

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public void addAttack(Attack attack) {
        attacks.add(attack);
    }

    public void removeAttack(Attack attack) {
        attacks.remove(attack);
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
        updateEntities();
        updateAttacks();
        removeDeadAttacks();
        checkBattle();
        removeDeadEntities();
    }

    private void checkBattle() {
        for (Entity entity : entities) {
            for (Attack attack : attacks) {
                if (entity.getParty() != attack.getOwner().getParty() && attack.isNotHit(entity) && HitBoxUtils.getInstance().checkCollide(entity.getHitBox(), attack.getHitBox())) {
                    attack.addHitEntity(entity);
                    entity.setHealth(entity.getHealth() - attack.getDamage());
                }
            }
        }
    }

    private void removeDeadEntities() {
        ArrayList<Entity> deadEntities = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity.getHealth() <= 0) {
                deadEntities.add(entity);
            }
        }
        for (Entity deadEntity : deadEntities) {
            removeEntity(deadEntity);
        }
    }

    private void removeDeadAttacks() {
        ArrayList<Attack> deadAttacks = new ArrayList<>();
        for (Attack attack : attacks) {
            if (attack.getTimeToLive() <= 0) {
                deadAttacks.add(attack);
            }
        }
        for (Attack deadAttack : deadAttacks) {
            removeAttack(deadAttack);
        }
    }

    private void updateAttacks() {
        for (Attack attack : attacks) {
            attack.update();
        }
    }

    private void updateEntities() {
        for (Entity entity : entities) {
            entity.update();
        }
    }

    private void drawEntities(Graphics2D g2d, Camera camera) {
        for (Entity entity : entities) {
            entity.draw(g2d, camera);
        }
    }

    public boolean isSolidTile(int x, int y) {
        int row = y / tileSize;
        int col = x / tileSize;
        return tiles[row][col].isSolid();
    }

    public void draw(Graphics2D g2d, Camera camera) {
        int startRow = camera.getY() / tileSize;
        int startCol = camera.getX() / tileSize;
        for (int i = startRow; i <= startRow + GameConstant.maxScreenRow && i < maxRow; i++) {
            for (int j = startCol; j <= startCol + GameConstant.maxScreenCol && j < maxCol; j++) {
                g2d.drawImage(tiles[i][j].getImage(), j * tileSize - camera.getX(), i * tileSize - camera.getY(), null);
            }
        }
        drawEntities(g2d, camera);
    }
}

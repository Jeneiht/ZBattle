package com.trongthien.zBattle.GameMap;

import com.trongthien.zBattle.Attack.Attack;
import com.trongthien.zBattle.character.Entity;
import com.trongthien.zBattle.component.HitBoxUtils;
import com.trongthien.zBattle.component.ResourceLoader;
import com.trongthien.zBattle.component.SharedContext;
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
    protected ArrayList<ArrayList<ArrayList<Tile>>> tiles;
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
        addEntity(SharedContext.getInstance().getCurrentPlayer());
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
        tileSet = new TileSet(tileSetPath, tileSize);
        System.out.println("tileSet.getMaxCol() = " + tileSet.getMaxCol());
        System.out.println("tileSet.getMaxRow() = " + tileSet.getMaxRow());
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
        SharedContext.getInstance().getCurrentPlayer().setX(spawnX);
        SharedContext.getInstance().getCurrentPlayer().setY(spawnY);
    }

    protected void load() {
        ArrayList<String> layers = XMLReader.getInstance().readMap(gameMapPath);
        tiles = new ArrayList<>(layers.size());
        for (int i = 0; i < layers.size(); i++) {
            tiles.add(new ArrayList<>(maxRow));
            for (int x = 0; x < maxRow; x++) {
                tiles.get(i).add(new ArrayList<>(maxCol));
                for (int y = 0; y < maxCol; y++) {
                    tiles.get(i).get(x).add(null);
                }
            }
        }
        for (int i = 0; i < layers.size(); i++) {
            String[] lines = layers.get(i).split("\\s+");
            for (int x = 0; x < maxRow; x++) {
                String[] nums = lines[x + 1].split(",");
                for (int y = 0; y < maxCol; y++) {
                    int id = Integer.parseInt(nums[y]);
                    tiles.get(i).get(x).set(y, getTile(id));
                }
            }
        }
        //loadSolidTiles();
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
        scanner.close();
    }

    private Tile getTile(int id) {
        if (id > 0) {
            id--;
        }
        if (id == 0) return null;
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
        //return tiles[row][col].isSolid();
        return false;
    }


    public void draw(Graphics2D g2d, Camera camera) {
        drawWorld(g2d, camera);
        drawEntities(g2d, camera);
    }

    private void drawWorld(Graphics2D g2d, Camera camera) {
        int startRow = camera.getY() / tileSize;
        int startCol = camera.getX() / tileSize;
        for (ArrayList<ArrayList<Tile>> tile : tiles) {
            for (int x = startRow; x <= startRow + GameConstant.maxScreenRow && x < maxRow; ++x) {
                for (int y = startCol; y <= startCol + GameConstant.maxScreenCol && y < maxCol; ++y) {
                    if (tile.get(x).get(y) != null) {
                        g2d.drawImage(tile.get(x).get(y).getImage(), y * tileSize - camera.getX(), x * tileSize - camera.getY(), null);
                    }
                }
            }
        }
    }
}

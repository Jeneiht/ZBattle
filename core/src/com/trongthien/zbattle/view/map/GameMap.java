package com.trongthien.zbattle.view.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.trongthien.zbattle.controller.combat.attack.Attack;
import com.trongthien.zbattle.common.io.XMLReader;
import com.trongthien.zbattle.model.Entity;
import com.trongthien.zbattle.controller.combat.hitbox.HitBoxUtils;
import com.trongthien.zbattle.common.SharedContext;
import com.trongthien.zbattle.common.constant.GameConstant;
import lombok.Getter;

import java.util.*;

@Getter
public abstract class GameMap {
    protected int maxCol;
    protected int maxRow;
    protected int tileSize;
    protected int width;
    protected int height;
    protected int numberOfLayers;
    protected ArrayList<ArrayList<ArrayList<Tile>>> tiles;

    protected String coverTilesPath;
    protected String tileSetPath;
    protected String gameMapPath;
    protected TileSet tileSet;
    protected int spawnX;
    protected int spawnY;
    ArrayList<Integer> coverTiles = new ArrayList<>();
    protected ArrayList<Entity> entities;
    protected ArrayList<Attack> attacks;


    public GameMap() {
        entities = new ArrayList<>();
        attacks = new ArrayList<>();
        addEntity(SharedContext.getInstance().getCurrentPlayer());
        setGameMapPath();
        setCoverTilesPath();
        setTileSetPath();
        setMaxCol();
        setMaxRow();
        setTileSize();
        setSpawnX();
        setSpawnY();
        spawnPlayer();
        width = maxCol * tileSize;
        height = maxRow * tileSize;
        tileSet = new TileSet(tileSetPath, tileSize);
        load();
        loadEntities();
    }

    protected abstract void setCoverTilesPath();

    protected abstract void setMaxCol();

    protected abstract void setMaxRow();

    protected abstract void setTileSize();

    protected abstract void setGameMapPath();

    protected abstract void setTileSetPath();

    protected abstract void setSpawnX();

    protected abstract void setSpawnY();

    public abstract void loadEntities();

    private void spawnPlayer() {
        SharedContext.getInstance().getCurrentPlayer().setX(spawnX);
        SharedContext.getInstance().getCurrentPlayer().setY(spawnY);
    }

    protected void load() {
        String coverTilesMap = XMLReader.getInstance().readMap(coverTilesPath).get(0);
        String[] lines = coverTilesMap.split("\\s+");
        for (int i = 1; i < lines.length; i++) {
            for (String num : lines[i].split(",")) {
                if (Integer.parseInt(num) > 0) {
                    coverTiles.add(Integer.parseInt(num));
                }
            }
        }
        List<String> layers = XMLReader.getInstance().readMap(gameMapPath);
        numberOfLayers = layers.size();
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
            lines = layers.get(i).split("\\s+");
            for (int y = 0; y < maxRow; y++) {
                String[] nums = lines[maxRow - y].split(",");
                for (int x = 0; x < maxCol; x++) {
                    int id = Integer.parseInt(nums[x]);
                    tiles.get(i).get(y).set(x, getTile(id));
                }
            }
        }
    }

    private Tile getTile(int id) {
        if (id > 0) {
            id--;
        } else {
            return null;
        }
        id %= tileSet.getMaxCol() * tileSet.getMaxRow();
        int x = id % tileSet.getMaxCol();
        int y = id / tileSet.getMaxRow();
        Tile tile = new Tile(tileSet, x, y, tileSize, tileSize);
        if (coverTiles.contains(id + 1)) {
            tile.setCover(true);
        }
        return tile;
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

    public boolean isSolidTile(int x, int y) {
        int row = y / tileSize;
        int col = x / tileSize;
        if (row < 0 || col < 0 || row >= maxRow || col >= maxCol) {
            return true;
        }
        return tiles.get(0).get(row).get(col) != null;
    }


    public void draw(SpriteBatch spriteBatch, Camera camera) {
        drawTiles(spriteBatch, camera);
        drawEntities(spriteBatch, camera);
        drawCoverTiles(spriteBatch, camera);
    }

    private void drawCoverTiles(SpriteBatch spriteBatch, Camera camera) {
        int startRow = camera.getY() / tileSize;
        int startCol = camera.getX() / tileSize;
        for (ArrayList<ArrayList<Tile>> tile : tiles) {
            for (int y = startRow; y <= startRow + GameConstant.maxScreenRow && y < maxRow; ++y) {
                for (int x = startCol; x <= startCol + GameConstant.maxScreenCol && x < maxCol; ++x) {
                    if (tile.get(y).get(x) != null && tile.get(y).get(x).isCover()) {
                        spriteBatch.draw(tile.get(y).get(x).getTextureRegion(), x * tileSize - camera.getX(), y * tileSize - camera.getY());
                    }
                }
            }
        }
    }

    private void drawTiles(SpriteBatch spriteBatch, Camera camera) {
        int startRow = camera.getY() / tileSize;
        int startCol = camera.getX() / tileSize;
        for (ArrayList<ArrayList<Tile>> tile : tiles) {
            for (int y = startRow; y <= startRow + GameConstant.maxScreenRow && y < maxRow; ++y) {
                for (int x = startCol; x <= startCol + GameConstant.maxScreenCol && x < maxCol; ++x) {
                    if (tile.get(y).get(x) != null) {
                        spriteBatch.draw(tile.get(y).get(x).getTextureRegion(), x * tileSize - camera.getX(), y * tileSize - camera.getY());
                    }
                }
            }
        }

    }

    private void drawEntities(SpriteBatch spriteBatch, Camera camera) {
        for (Entity entity : entities) {
            entity.draw(spriteBatch, camera);
        }
    }
}

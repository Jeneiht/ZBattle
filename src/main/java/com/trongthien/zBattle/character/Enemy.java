package com.trongthien.zBattle.character;

import com.trongthien.zBattle.GameMap.Camera;
import com.trongthien.zBattle.GameMap.GameMap;
import com.trongthien.zBattle.GameMap.Tile;
import com.trongthien.zBattle.GameMap.TileSet;
import com.trongthien.zBattle.component.AnimationCounter;
import com.trongthien.zBattle.component.CollisionChecker;
import com.trongthien.zBattle.component.HitBoxUtils;
import com.trongthien.zBattle.component.SharedCurrentContext;
import com.trongthien.zBattle.constant.GameConstant;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public abstract class Enemy extends Entity {
    public int id;
    protected int health;
    protected String enemyTileSetPath;
    TileSet enemyTileSet;
    protected int tileX, tileY;
    EnemyState enemyState;
    protected int walkSpeed, runSpeed, idleSpeed;
    protected Map<Map<EnemyState, Direction>, Integer> maxFrame = new HashMap<>();
    protected Map<Map<EnemyState, Direction>, Integer> mapTileY = new HashMap<>();
    boolean animationChanged;
    AnimationCounter animationCounter;

    public Enemy(GameMap gameMap, int x, int y, int id) {
        this.id = id;
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        collisionChecker = new CollisionChecker(gameMap);
        changeDirection = false;
        setHealth();
        setEnemyTileSetPath();
        setHeight();
        setWidth();
        setBodyHitBox();
        setWalkSpeed();
        setRunSpeed();
        setIdleSpeed();
        setCurrentMovement();
        enemyTileSet = new TileSet(enemyTileSetPath, width);
        enemyState = EnemyState.WALK;
        direction = Direction.values()[(int) (Math.random() * Direction.values().length)];
        animationCounter = new AnimationCounter(GameConstant.animationSpeed);
        load();
    }
    protected abstract void setCurrentMovement();
    protected abstract void setRunSpeed();

    protected abstract void setWalkSpeed();

    protected abstract void setIdleSpeed();

    protected abstract void setHealth();

    protected abstract void setEnemyTileSetPath();

    protected abstract void setBodyHitBox();

    protected abstract void setWidth();

    protected abstract void setHeight();

    private void load() {
        loadFrameInfo();
    }

    public void update() {
        checkAttacked();
        EnemyState previousState = enemyState;
        Direction previousDirection = direction;
        updateSpeed();
        updateDirection();
        currentMovement.move(this);
        if (previousState != enemyState || previousDirection != direction) {
            animationChanged = true;
        }
        boolean animationBlocked = false;
        if (enemyState == EnemyState.ATTACK) {
            animationBlocked = true;
        }
        if (animationChanged || animationCounter.getMaxFrame() == 0) {
            animationCounter.start(maxFrame.get(Map.of(enemyState, Direction.force(direction))), animationBlocked);
        } else {
            animationCounter.update();
        }
    }

    private void checkAttacked() {
        if (isAttacked()) {
            health -= SharedCurrentContext.getInstance().getCurrentPlayer().getDamage();
            if (health <= 0) {
                die();
            }
        }
    }
    private boolean isAttacked() {
        if(SharedCurrentContext.getInstance().getCurrentPlayer().getPlayerState()==PlayerState.ATTACKA) {
            if(HitBoxUtils.getInstance().isColliding(SharedCurrentContext.getInstance().getCurrentPlayer(), this, SharedCurrentContext.getInstance().getCurrentPlayer().getAttackAHitBox(), this.getBodyHitBox())) {
                return true;
            }
        }
        return false;
    }
    private void die() {
        health = 0;
    }
    private void updateSpeed() {
        if (enemyState == EnemyState.WALK) {
            speed = walkSpeed;
        } else if (enemyState == EnemyState.RUN) {
            speed = runSpeed;
        } else if (enemyState == EnemyState.IDLE) {
            speed = idleSpeed;
        }
    }

    private void updateDirection() {
        if (changeDirection) {
            changeDirection = false;
            direction = Direction.values()[(int) (Math.random() * Direction.values().length)];
        }
    }
    private void loadFrameInfo() {
        //init mapTileY
        mapTileY.put(Map.of(EnemyState.IDLE, Direction.DOWN), 0);
        mapTileY.put(Map.of(EnemyState.IDLE, Direction.RIGHT), 0);
        mapTileY.put(Map.of(EnemyState.IDLE, Direction.UP), 0);
        mapTileY.put(Map.of(EnemyState.IDLE, Direction.LEFT), 0);
        mapTileY.put(Map.of(EnemyState.WALK, Direction.DOWN), 1);
        mapTileY.put(Map.of(EnemyState.WALK, Direction.RIGHT), 1);
        mapTileY.put(Map.of(EnemyState.WALK, Direction.UP), 1);
        mapTileY.put(Map.of(EnemyState.WALK, Direction.LEFT), 1);
        mapTileY.put(Map.of(EnemyState.ATTACK, Direction.DOWN), 2);
        mapTileY.put(Map.of(EnemyState.ATTACK, Direction.RIGHT), 2);
        mapTileY.put(Map.of(EnemyState.ATTACK, Direction.UP), 2);
        mapTileY.put(Map.of(EnemyState.ATTACK, Direction.LEFT), 2);


        //init maxFrame
        maxFrame.put(Map.of(EnemyState.IDLE, Direction.DOWN), 3);
        maxFrame.put(Map.of(EnemyState.IDLE, Direction.RIGHT), 3);
        maxFrame.put(Map.of(EnemyState.IDLE, Direction.UP), 3);
        maxFrame.put(Map.of(EnemyState.IDLE, Direction.LEFT), 3);
        maxFrame.put(Map.of(EnemyState.WALK, Direction.DOWN), 3);
        maxFrame.put(Map.of(EnemyState.WALK, Direction.RIGHT), 3);
        maxFrame.put(Map.of(EnemyState.WALK, Direction.UP), 3);
        maxFrame.put(Map.of(EnemyState.WALK, Direction.LEFT), 3);
        maxFrame.put(Map.of(EnemyState.ATTACK, Direction.DOWN), 3);
        maxFrame.put(Map.of(EnemyState.ATTACK, Direction.RIGHT), 3);
        maxFrame.put(Map.of(EnemyState.ATTACK, Direction.UP), 3);
        maxFrame.put(Map.of(EnemyState.ATTACK, Direction.LEFT), 3);
    }

    private void findTile() {
        tileY = mapTileY.get(Map.of(enemyState, Direction.force(direction)));
        tileX = animationCounter.getFrame();
    }

    public void draw(Graphics2D g2d, Camera camera) {
        findTile();
        Tile tile = new Tile(enemyTileSet, tileX, tileY, width, height);
        BufferedImage enemyImage = tile.getImage();
        if (x >= camera.getX() && x + width <= camera.getX() + GameConstant.screenWidth && y >= camera.getY() && y + height <= camera.getY() + GameConstant.screenHeight) {
            g2d.drawImage(enemyImage, x - camera.getX(), y - camera.getY(), null);
        }
    }
}

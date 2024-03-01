package com.trongthien.zBattle.character;

import com.trongthien.zBattle.GameMap.Camera;
import com.trongthien.zBattle.GameMap.GameMap;
import com.trongthien.zBattle.GameMap.Tile;
import com.trongthien.zBattle.GameMap.TileSet;
import com.trongthien.zBattle.UI.HealthBar;
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
    protected String enemyTileSetPath;
    protected TileSet enemyTileSet;
    protected int tileX, tileY;
    protected EnemyState enemyState;
    protected int walkSpeed, runSpeed, idleSpeed;
    protected Map<EnemyState, Integer> maxFrame = new HashMap<>();
    protected Map<Map<EnemyState, Direction>, Integer> mapTileY = new HashMap<>();
    private AnimationCounter animationCounter;
    private boolean enemyStateBlocked;

    public Enemy(int x, int y) {
        party = Party.ENEMY;
        this.x = x;
        this.y = y;
        setHealth();
        healthBar = new HealthBar(this);
        setEnemyTileSetPath();
        setHeight();
        setWidth();
        setWalkSpeed();
        setRunSpeed();
        setIdleSpeed();
        setCurrentMovement();
        enemyTileSet = new TileSet(enemyTileSetPath, width);
        enemyState = EnemyState.WALK;
        direction = Direction.values()[(int) (Math.random() * Direction.values().length)];
        loadFrameInfo();
        animationCounter = new AnimationCounter(GameConstant.animationSpeed);
        animationCounter.start(maxFrame.get(enemyState));
    }

    protected abstract void setCurrentMovement();

    protected abstract void setRunSpeed();

    protected abstract void setWalkSpeed();

    protected abstract void setIdleSpeed();

    protected abstract void setHealth();

    protected abstract void setEnemyTileSetPath();

    protected abstract void setWidth();

    protected abstract void setHeight();


    @Override
    public void update() {
        EnemyState previousState = enemyState;
        updateSpeed();
        currentMovement.move(this);
        if (animationCounter.isFinished()) {
            enemyStateBlocked = false;
        }
        if (!enemyStateBlocked) {
            updateEnemyState();
        }
        if (enemyState == EnemyState.ATTACK) {
            attacking = true;
        } else {
            attacking = false;
        }
        if (enemyState != previousState || animationCounter == null) {
            animationCounter.start(maxFrame.get(enemyState));
        } else {
            animationCounter.update();
        }
        doAttack();
        healthBar.update();
    }

    protected abstract void updateEnemyState();

    private void updateSpeed() {
        if (enemyState == EnemyState.WALK) {
            speed = walkSpeed;
        } else if (enemyState == EnemyState.RUN) {
            speed = runSpeed;
        } else if (enemyState == EnemyState.IDLE) {
            speed = idleSpeed;
        }
    }

    private void doAttack() {
        if (attacking && animationCounter.isStarting()) {
            setCurrentAttack(enemyState);
            SharedCurrentContext.getInstance().getCurrentGameMap().addAttack(currentAttack);
        }
    }

    protected abstract void setCurrentAttack(EnemyState enemyState);

    protected abstract void loadFrameInfo();

    private void findTile() {
        tileY = mapTileY.get(Map.of(enemyState, Direction.force(direction)));
        tileX = animationCounter.getFrame();
    }

    @Override
    public void draw(Graphics2D g2d, Camera camera) {
        findTile();
        Tile tile = new Tile(enemyTileSet, tileX, tileY, width, height);
        BufferedImage enemyImage = tile.getImage();
        if (x >= camera.getX() && x + width <= camera.getX() + GameConstant.screenWidth && y >= camera.getY() && y + height <= camera.getY() + GameConstant.screenHeight) {
            g2d.drawImage(enemyImage, x - camera.getX(), y - camera.getY(), null);
            healthBar.draw(g2d, x - camera.getX(), y - camera.getY() - 10, width, 5);
        }
    }
}

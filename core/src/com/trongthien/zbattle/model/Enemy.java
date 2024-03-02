package com.trongthien.zbattle.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.trongthien.zbattle.controller.movement.Direction;
import com.trongthien.zbattle.model.constant.EnemyState;
import com.trongthien.zbattle.view.map.Camera;
import com.trongthien.zbattle.view.map.Tile;
import com.trongthien.zbattle.view.map.TileSet;
import com.trongthien.zbattle.view.entity.HealthBar;
import com.trongthien.zbattle.view.entity.Animation;
import com.trongthien.zbattle.common.SharedContext;
import com.trongthien.zbattle.common.constant.GameConstant;
import lombok.Getter;
import lombok.Setter;

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
    private Animation animation;
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
        animation = new Animation(GameConstant.animationSpeed);
        animation.start(maxFrame.get(enemyState));
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
        if (animation.isFinished()) {
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
        if (enemyState != previousState || animation == null) {
            animation.start(maxFrame.get(enemyState));
        } else {
            animation.update();
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
        if (attacking && animation.isStarting()) {
            setCurrentAttack(enemyState);
            SharedContext.getInstance().getCurrentGameMap().addAttack(currentAttack);
        }
    }

    protected abstract void setCurrentAttack(EnemyState enemyState);

    protected abstract void loadFrameInfo();

    private void findTile() {
        tileY = mapTileY.get(Map.of(enemyState, Direction.force(direction)));
        tileX = animation.getFrame();
    }

    @Override
    public void draw(SpriteBatch spriteBatch, Camera camera) {
        findTile();
        Tile tile = new Tile(enemyTileSet, tileX, tileY, width, height);
        TextureRegion enemyImage = tile.getTextureRegion();
        if (x >= camera.getX() && x + width <= camera.getX() + GameConstant.screenWidth && y >= camera.getY() && y + height <= camera.getY() + GameConstant.screenHeight) {
            spriteBatch.draw(enemyImage, x - camera.getX(), y - camera.getY(), null);
            //healthBar.draw(spriteBatch, x - camera.getX(), y - camera.getY() - 10, width, 5);
        }
    }
}

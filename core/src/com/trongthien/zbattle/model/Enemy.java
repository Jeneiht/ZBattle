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
    protected int walkSpeed, runSpeed, idleSpeed,attackSpeed;
    protected Map<EnemyState, Integer> maxFrame = new HashMap<>();
    protected Map<Map<EnemyState, Direction>, Integer> mapTileY = new HashMap<>();
    private Animation animation;
    private boolean enemyStateBlocked;
    protected int aggroRange;

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
        setAttackSpeed();
        setAggroRange();
        enemyTileSet = new TileSet(enemyTileSetPath, width);
        enemyState = EnemyState.IDLE;
        direction = Direction.values()[(int) (Math.random() * Direction.values().length)];
        loadFrameInfo();
        animation = new Animation(GameConstant.animationSpeed);
        animation.start(maxFrame.get(enemyState));
    }
    protected abstract void setAggroRange();
    protected abstract void setRunSpeed();

    protected abstract void setWalkSpeed();

    protected abstract void setIdleSpeed();

    protected abstract void setHealth();

    protected abstract void setEnemyTileSetPath();

    protected abstract void setWidth();

    protected abstract void setHeight();
    protected abstract void setAttackSpeed();


    @Override
    public void update() {
        EnemyState previousState = enemyState;
        updateSpeed();
        updateMovement();
        currentMovement.move(this);
        if (animation.isFinished()) {
            enemyStateBlocked = false;
        }
        if (!enemyStateBlocked) {
            updateEnemyState();
        }
        attacking = enemyState == EnemyState.ATTACK;
        if(enemyState == EnemyState.DIE||enemyState==EnemyState.ATTACK){
            enemyStateBlocked = true;
        }
        if (enemyState != previousState || animation == null) {
            assert animation != null;
            animation.start(maxFrame.get(enemyState));
        } else {
            animation.update();
        }
        doAttack();
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
        else if(enemyState == EnemyState.ATTACK){
            speed = attackSpeed;
        }
    }

    private void doAttack() {
        if (attacking && animation.isStarting()) {
            setCurrentAttack(enemyState);
            SharedContext.getInstance().getCurrentGameMap().addAttack(currentAttack);
        }
    }

    protected abstract void setCurrentAttack(EnemyState enemyState);
    protected abstract void updateMovement();
    protected abstract void loadFrameInfo();

    private void findTile() {
        tileY = mapTileY.get(Map.of(enemyState, Direction.force(direction)));
        tileX = animation.getFrame();
    }
    @Override
    public void drawHealthBar(SpriteBatch spriteBatch){
        healthBar.draw(spriteBatch,x,y+height-2,32,2);
    }
    @Override
    public void draw(SpriteBatch spriteBatch) {
        findTile();
        Tile tile = new Tile(enemyTileSet, tileX, tileY, width, height);
        TextureRegion enemyImage = tile.getTextureRegion();
        spriteBatch.draw(enemyImage, x, y);
    }
}

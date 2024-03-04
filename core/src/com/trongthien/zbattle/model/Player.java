package com.trongthien.zbattle.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.trongthien.zbattle.controller.movement.Direction;
import com.trongthien.zbattle.model.constant.PlayerState;
import com.trongthien.zbattle.view.map.Camera;
import com.trongthien.zbattle.view.map.Tile;
import com.trongthien.zbattle.view.map.TileSet;
import com.trongthien.zbattle.controller.movement.impl.PlayerMovement;
import com.trongthien.zbattle.view.entity.HealthBar;
import com.trongthien.zbattle.view.entity.Animation;
import com.trongthien.zbattle.common.SharedContext;
import com.trongthien.zbattle.common.constant.GameConstant;
import com.trongthien.zbattle.common.io.KeyHandler;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public abstract class Player extends Entity {
    protected PlayerState playerState;
    //state: idle, walk, run, attackA, attackB, attackC, idleDrawn, walkDrawn, hurtDrawn, sheath
    protected String playerTileSetPath;
    protected int runSpeed, walkSpeed, idleSpeed;
    private boolean playerStateBlocked;
    protected TileSet playerTileSet;
    protected int tileX, tileY;
    protected Map<PlayerState, Integer> maxFrame = new HashMap<>();
    protected Map<Map<PlayerState, Direction>, Integer> mapTileY = new HashMap<>();
    Animation animation;

    protected Player() {
        party = Party.PLAYER;
        currentMovement = new PlayerMovement();
        playerStateBlocked = false;
        setHealth();
        healthBar = new HealthBar(this);
        setPlayerTileSetPath();
        setHeight();
        setWidth();
        setIdleSpeed();
        setWalkSpeed();
        setRunSpeed();
        playerTileSet = new TileSet(playerTileSetPath, width);
        playerState = PlayerState.IDLE;
        direction = Direction.DOWN;
        loadFrameInfo();
        animation = new Animation(GameConstant.animationSpeed);
        animation.start(maxFrame.get(playerState));
    }

    protected abstract void setHealth();

    protected abstract void setPlayerTileSetPath();

    protected abstract void setRunSpeed();

    protected abstract void setWalkSpeed();

    protected abstract void setIdleSpeed();

    protected abstract void setWidth();

    protected abstract void setHeight();

    protected abstract void setCurrentAttack(PlayerState playerState);

    @Override
    public void update() {
        PlayerState previousState = playerState;
        updateSpeed();
        currentMovement.move(this);
        if (animation.isFinished()) {
            playerStateBlocked = false;
        }
        if (!playerStateBlocked) {
            updatePlayerState();
        }
        if (playerState == PlayerState.ATTACKA) {
            attacking = true;
            playerStateBlocked = true;
        } else {
            attacking = false;
        }
        if (previousState != playerState) {
            animation.start(maxFrame.get(playerState));
        } else {
            animation.update();
        }
        doAttack();
        healthBar.update();
    }

    private void updateSpeed() {
        if (KeyHandler.getInstance().isUp() || KeyHandler.getInstance().isDown() || KeyHandler.getInstance().isLeft() || KeyHandler.getInstance().isRight()) {
            if (KeyHandler.getInstance().isShift()) {
                speed = runSpeed;
            } else {
                speed = walkSpeed;
            }
        } else {
            speed = idleSpeed;
        }
    }

    private void updatePlayerState() {
        if (speed == idleSpeed) {
            playerState = PlayerState.IDLE;
        } else if (speed == walkSpeed) {
            playerState = PlayerState.WALK;
        } else if (speed == runSpeed) {
            playerState = PlayerState.RUN;
        }
        if (KeyHandler.getInstance().isSpace()) {
            playerState = PlayerState.ATTACKA;
        }
    }

    private void doAttack() {
        if (attacking && animation.isStarting()) {
            setCurrentAttack(playerState);
            SharedContext.getInstance().getCurrentGameMap().addAttack(currentAttack);
        }
    }

    protected abstract void loadFrameInfo();

    private void findTile() {
        tileY = mapTileY.get(Map.of(playerState, Direction.force(direction)));
        tileX = animation.getFrame();
    }

    @Override
    public void draw(SpriteBatch spriteBatch, Camera camera) {
        findTile();
        Tile tile = new Tile(playerTileSet, tileX, tileY, width, height);
        TextureRegion heroImage = tile.getTextureRegion();
        spriteBatch.draw(heroImage, x - camera.getX(), y - camera.getY());
        healthBar.draw(spriteBatch, 20, 20, 200, 20);
    }
}

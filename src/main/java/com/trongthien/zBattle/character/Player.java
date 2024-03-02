package com.trongthien.zBattle.character;

import com.trongthien.zBattle.Movement.PlayerMovement;
import com.trongthien.zBattle.GameMap.Camera;
import com.trongthien.zBattle.GameMap.Tile;
import com.trongthien.zBattle.GameMap.TileSet;
import com.trongthien.zBattle.UI.HealthBar;
import com.trongthien.zBattle.component.AnimationCounter;
import com.trongthien.zBattle.component.SharedContext;
import com.trongthien.zBattle.constant.GameConstant;
import com.trongthien.zBattle.key.KeyHandler;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public abstract class Player extends Entity {
    protected PlayerState playerState;
    //state: idle, walk, run, attackA, attackB, attackC, idleDrawn, walkDrawn, hurtDrawn, sheath
    protected String playerTileSetPath;
    protected int runSpeed;
    protected int walkSpeed;
    protected int idleSpeed;
    private boolean playerStateBlocked;
    protected TileSet playerTileSet;
    protected int tileX, tileY;
    protected Map<PlayerState, Integer> maxFrame = new HashMap<>();
    protected Map<Map<PlayerState, Direction>, Integer> mapTileY = new HashMap<>();
    AnimationCounter animationCounter;

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
        animationCounter = new AnimationCounter(GameConstant.animationSpeed);
        animationCounter.start(maxFrame.get(playerState));
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
        if (animationCounter.isFinished()) {
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
            animationCounter.start(maxFrame.get(playerState));
        } else {
            animationCounter.update();
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
        if (attacking && animationCounter.isStarting()) {
            setCurrentAttack(playerState);
            SharedContext.getInstance().getCurrentGameMap().addAttack(currentAttack);
        }
    }

    protected abstract void loadFrameInfo();

    private void findTile() {
        tileY = mapTileY.get(Map.of(playerState, Direction.force(direction)));
        tileX = animationCounter.getFrame();
    }

    @Override
    public void draw(Graphics2D g2d, Camera camera) {
        findTile();
        Tile tile = new Tile(playerTileSet, tileX, tileY, width, height);
        BufferedImage heroImage = tile.getImage();
        g2d.drawImage(heroImage, x - camera.getX(), y - camera.getY(), null);
        healthBar.draw(g2d, 20, 20, 200, 20);
    }
}

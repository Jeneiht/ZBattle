package com.trongthien.zBattle.character;

import com.trongthien.zBattle.GameMap.Camera;
import com.trongthien.zBattle.GameMap.Tile;
import com.trongthien.zBattle.GameMap.TileSet;
import com.trongthien.zBattle.component.AnimationUtils;
import com.trongthien.zBattle.constant.GameConstant;
import com.trongthien.zBattle.key.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;


public class Player extends Entity {
    public PlayerState playerState;
    //state: idle, walk, run, attackA, attackB, attackC, idleDrawn, walkDrawn, hurtDrawn, sheath
    public String playerTileSetPath;
    public TileSet playerTileSet;
    public int tileX, tileY;
    public Map<Map<PlayerState, Direction>, Integer> maxFrame = new HashMap<>();
    public Map<Map<PlayerState, Direction>, Integer> mapTileY = new HashMap<>();

    protected void load() {
        getSolidArea();
        loadFrameInfo();
        findTile();
    }

    public void update() {
        boolean animationChanged = false;
        PlayerState previousState = playerState;
        Direction previousDirection = direction;
        updateSpeed();
        if (!AnimationUtils.getInstance().isBlocked() || AnimationUtils.getInstance().endAnimation()) {
            updatePlayerState();
        }
        updateDirection();
        move();
        if (previousState != playerState || previousDirection != direction) {
            animationChanged = true;
        }
        boolean animationBlocked = false;
        if (playerState == PlayerState.ATTACKA) {
            animationBlocked = true;
        }
        if (animationChanged) {
            AnimationUtils.getInstance().startAnimation(GameConstant.animationSpeed, maxFrame.get(Map.of(playerState, direction)), animationBlocked);
        } else {
            AnimationUtils.getInstance().update();
        }
    }

    private void updateDirection() {
        if (KeyHandler.getInstance().isUp()) {
            direction = Direction.UP;
        }
        if (KeyHandler.getInstance().isDown()) {
            direction = Direction.DOWN;
        }
        if (KeyHandler.getInstance().isLeft()) {
            direction = Direction.LEFT;
        }
        if (KeyHandler.getInstance().isRight()) {
            direction = Direction.RIGHT;
        }
    }

    private void move() {
        if (KeyHandler.getInstance().isUp()) {
            y -= speed;
            getSolidArea();
            while (collisionChecker.checkCollisionTop(this)) {
                y++;
                getSolidArea();
            }
        }
        if (KeyHandler.getInstance().isDown()) {
            y += speed;
            getSolidArea();
            while (collisionChecker.checkCollisionBottom(this)) {
                y--;
                getSolidArea();
            }
        }
        if (KeyHandler.getInstance().isLeft()) {
            x -= speed;
            getSolidArea();
            while (collisionChecker.checkCollisionLeft(this)) {
                x++;
                getSolidArea();
            }
        }
        if (KeyHandler.getInstance().isRight()) {
            x += speed;
            getSolidArea();
            while (collisionChecker.checkCollisionRight(this)) {
                x--;
                getSolidArea();
            }
        }
    }

    private void updateSpeed() {
        if (KeyHandler.getInstance().isUp() || KeyHandler.getInstance().isDown() || KeyHandler.getInstance().isLeft() || KeyHandler.getInstance().isRight()) {
            if (KeyHandler.getInstance().isRun()) {
                speed = 3;
            } else {
                speed = 2;
            }
        } else {
            speed = 0;
        }
    }

    private void updatePlayerState() {
        if (speed == 0) {
            playerState = PlayerState.IDLE;
        } else if (speed == 2) {
            playerState = PlayerState.WALK;
        } else if (speed == 3) {
            playerState = PlayerState.RUN;
        }
        if (KeyHandler.getInstance().isAttackA()) {
            playerState = PlayerState.ATTACKA;
        }
    }

    private void getSolidArea() {
        solidArea = new Rectangle(x + solidX, y + solidY, solidWidth, solidHeight);
    }

    private void loadFrameInfo() {
        //init mapTileY
        mapTileY.put(Map.of(PlayerState.IDLE, Direction.DOWN), 0);
        mapTileY.put(Map.of(PlayerState.IDLE, Direction.RIGHT), 1);
        mapTileY.put(Map.of(PlayerState.IDLE, Direction.UP), 2);
        mapTileY.put(Map.of(PlayerState.IDLE, Direction.LEFT), 3);
        mapTileY.put(Map.of(PlayerState.WALK, Direction.DOWN), 4);
        mapTileY.put(Map.of(PlayerState.WALK, Direction.RIGHT), 5);
        mapTileY.put(Map.of(PlayerState.WALK, Direction.UP), 6);
        mapTileY.put(Map.of(PlayerState.WALK, Direction.LEFT), 7);
        mapTileY.put(Map.of(PlayerState.RUN, Direction.DOWN), 8);
        mapTileY.put(Map.of(PlayerState.RUN, Direction.RIGHT), 9);
        mapTileY.put(Map.of(PlayerState.RUN, Direction.UP), 10);
        mapTileY.put(Map.of(PlayerState.RUN, Direction.LEFT), 11);
        mapTileY.put(Map.of(PlayerState.ATTACKA, Direction.DOWN), 12);
        mapTileY.put(Map.of(PlayerState.ATTACKA, Direction.RIGHT), 13);
        mapTileY.put(Map.of(PlayerState.ATTACKA, Direction.UP), 14);
        mapTileY.put(Map.of(PlayerState.ATTACKA, Direction.LEFT), 15);

        //init maxFrame
        maxFrame.put(Map.of(PlayerState.IDLE, Direction.DOWN), 8);
        maxFrame.put(Map.of(PlayerState.IDLE, Direction.RIGHT), 8);
        maxFrame.put(Map.of(PlayerState.IDLE, Direction.UP), 8);
        maxFrame.put(Map.of(PlayerState.IDLE, Direction.LEFT), 8);
        maxFrame.put(Map.of(PlayerState.WALK, Direction.DOWN), 8);
        maxFrame.put(Map.of(PlayerState.WALK, Direction.RIGHT), 8);
        maxFrame.put(Map.of(PlayerState.WALK, Direction.UP), 8);
        maxFrame.put(Map.of(PlayerState.WALK, Direction.LEFT), 8);
        maxFrame.put(Map.of(PlayerState.RUN, Direction.DOWN), 8);
        maxFrame.put(Map.of(PlayerState.RUN, Direction.RIGHT), 8);
        maxFrame.put(Map.of(PlayerState.RUN, Direction.UP), 8);
        maxFrame.put(Map.of(PlayerState.RUN, Direction.LEFT), 8);
        maxFrame.put(Map.of(PlayerState.ATTACKA, Direction.DOWN), 6);
        maxFrame.put(Map.of(PlayerState.ATTACKA, Direction.RIGHT), 6);
        maxFrame.put(Map.of(PlayerState.ATTACKA, Direction.UP), 6);
        maxFrame.put(Map.of(PlayerState.ATTACKA, Direction.LEFT), 6);
    }

    private void findTile() {
        tileY = mapTileY.get(Map.of(playerState, direction));
        tileX = AnimationUtils.getInstance().getFrame();
    }

    public void draw(Graphics2D g2d, Camera camera) {
        findTile();
        Tile tile = new Tile(playerTileSet, tileX, tileY, width, height);
        BufferedImage heroImage = tile.getImage();
        g2d.drawImage(heroImage, x - camera.getX(), y - camera.getY(), null);
    }
}

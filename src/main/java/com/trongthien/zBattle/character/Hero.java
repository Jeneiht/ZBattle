package com.trongthien.zBattle.character;

import com.trongthien.zBattle.GameMap.Camera;
import com.trongthien.zBattle.GameMap.Tile;
import com.trongthien.zBattle.GameMap.TileSet;
import com.trongthien.zBattle.GameMap.World;
import com.trongthien.zBattle.component.AnimationUtils;
import com.trongthien.zBattle.component.CollisionChecker;
import com.trongthien.zBattle.constant.GameConstant;
import com.trongthien.zBattle.key.KeyHandler;
import com.trongthien.zBattle.screen.MainPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;


public class Hero extends Entity {
    public final String heroTileSetPath = "/character/hero/hero_tiles.png";

    public TileSet heroTileSet = new TileSet(heroTileSetPath);
    public int tileX, tileY;
    public Map<Map<State, Direction>, Integer> maxFrame;
    public Map<Map<State, Direction>, Integer> mapTileY;
    public AnimationUtils animationUtils;

    public Hero(World world, int x, int y) {
        this.world = world;
        collisionChecker = new CollisionChecker(world);
        this.x = x;
        this.y = y;
        width = 64;
        height = 64;
        speed = 3;
        solidX = 24;
        solidY = 32;
        solidWidth = 16;
        solidHeight = 16;
        getSolidArea();
        state = State.IDLE;
        direction = Direction.DOWN;
        loadFrameInfo();
        animationUtils = new AnimationUtils(GameConstant.animationSpeed);
        findTile();
    }

    public void update() {
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
        updateDirection();
        updateState();
        animationUtils.update(maxFrame.get(Map.of(state, direction)));
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

    private void getSolidArea() {
        solidArea = new Rectangle(x + solidX, y + solidY, solidWidth, solidHeight);
    }

    private void updateState() {
        if (KeyHandler.getInstance().isUp() || KeyHandler.getInstance().isDown() || KeyHandler.getInstance().isLeft() || KeyHandler.getInstance().isRight()) {
            state = State.WALK;
        } else {
            state = State.IDLE;
        }
    }

    private void loadFrameInfo() {
        mapTileY = Map.of(
                Map.of(State.IDLE, Direction.DOWN), 0,
                Map.of(State.IDLE, Direction.RIGHT), 1,
                Map.of(State.IDLE, Direction.UP), 2,
                Map.of(State.IDLE, Direction.LEFT), 3,
                Map.of(State.WALK, Direction.DOWN), 4,
                Map.of(State.WALK, Direction.RIGHT), 5,
                Map.of(State.WALK, Direction.UP), 6,
                Map.of(State.WALK, Direction.LEFT), 7
        );
        maxFrame = Map.of(
                Map.of(State.IDLE, Direction.DOWN), 8,
                Map.of(State.IDLE, Direction.RIGHT), 8,
                Map.of(State.IDLE, Direction.UP), 8,
                Map.of(State.IDLE, Direction.LEFT), 8,
                Map.of(State.WALK, Direction.DOWN), 8,
                Map.of(State.WALK, Direction.RIGHT), 8,
                Map.of(State.WALK, Direction.UP), 8,
                Map.of(State.WALK, Direction.LEFT), 8
        );
    }

    private void findTile() {
        tileY = mapTileY.get(Map.of(state, direction));
        tileX = animationUtils.getFrame();
    }

    public void draw(Graphics2D g2d, Camera camera) {
        findTile();
        Tile tile = new Tile(heroTileSet, tileX, tileY, width, height);
        BufferedImage heroImage = tile.getImage();
        g2d.drawImage(heroImage, x - camera.getX(), y - camera.getY(), null);
    }
}

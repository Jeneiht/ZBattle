package com.trongthien.zBattle.character;

import Movement.NormalMovement;
import com.trongthien.zBattle.GameMap.Camera;
import com.trongthien.zBattle.GameMap.GameMap;
import com.trongthien.zBattle.GameMap.Tile;
import com.trongthien.zBattle.GameMap.TileSet;
import com.trongthien.zBattle.component.AnimationCounter;
import com.trongthien.zBattle.component.CollisionChecker;
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
    protected int health;
    protected int damage=100;
    protected PlayerState playerState;
    //state: idle, walk, run, attackA, attackB, attackC, idleDrawn, walkDrawn, hurtDrawn, sheath
    protected String playerTileSetPath;
    protected int runSpeed;
    protected int walkSpeed;
    protected int idleSpeed;
    protected TileSet playerTileSet;
    protected int tileX, tileY;
    protected Map<Map<PlayerState, Direction>, Integer> maxFrame = new HashMap<>();
    protected Map<Map<PlayerState, Direction>, Integer> mapTileY = new HashMap<>();
    AnimationCounter animationCounter;
    Map<Direction, HitBox> attackAHitBox = new HashMap<>();

    protected Player(GameMap gameMap) {
        this.gameMap = gameMap;
        collisionChecker = new CollisionChecker(gameMap);
        this.x = gameMap.getSpawnX();
        this.y = gameMap.getSpawnY();
        currentMovement = new NormalMovement();
        setHealth();
        setPlayerTileSetPath();
        setHeight();
        setWidth();
        setBodyHitBox();
        setAttackAHitBox();
        setIdleSpeed();
        setWalkSpeed();
        setRunSpeed();
        playerTileSet = new TileSet(playerTileSetPath, width);
        playerState = PlayerState.IDLE;
        direction = Direction.DOWN;
        animationCounter = new AnimationCounter(GameConstant.animationSpeed);
        load();
    }
    public HitBox getAttackAHitBox(){
        return attackAHitBox.get(Direction.force(direction));
    }
    protected abstract void setAttackAHitBox();
    protected abstract void setBodyHitBox();
    protected abstract void setHealth();

    protected abstract void setPlayerTileSetPath();

    protected abstract void setRunSpeed();

    protected abstract void setWalkSpeed();

    protected abstract void setIdleSpeed();

    protected abstract void setWidth();

    protected abstract void setHeight();

    private void load() {
        loadFrameInfo();
        findTile();
    }

    public void update() {
        boolean animationChanged = false;
        PlayerState previousState = playerState;
        Direction previousDirection = direction;
        updateSpeed();
        if (!animationCounter.isBlocked() || animationCounter.isEndAnimation()) {
            updatePlayerState();
        }
        updateDirection();
        currentMovement.move(this);
        if (previousState != playerState || previousDirection != direction) {
            animationChanged = true;
        }
        boolean animationBlocked = false;
        if (playerState == PlayerState.ATTACKA) {
            animationBlocked = true;
        }
        if (animationChanged || animationCounter.getMaxFrame() == 0) {
            animationCounter.start(maxFrame.get(Map.of(playerState, Direction.force(direction))), animationBlocked);
        } else {
            animationCounter.update();
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
        if (KeyHandler.getInstance().isUp() && KeyHandler.getInstance().isLeft() && !KeyHandler.getInstance().isRight() && !KeyHandler.getInstance().isDown()) {
            direction = Direction.UP_LEFT;
        }
        if (KeyHandler.getInstance().isUp() && KeyHandler.getInstance().isRight() && !KeyHandler.getInstance().isLeft() && !KeyHandler.getInstance().isDown()) {
            direction = Direction.UP_RIGHT;
        }
        if (KeyHandler.getInstance().isDown() && KeyHandler.getInstance().isLeft() && !KeyHandler.getInstance().isRight() && !KeyHandler.getInstance().isUp()) {
            direction = Direction.DOWN_LEFT;
        }
        if (KeyHandler.getInstance().isDown() && KeyHandler.getInstance().isRight() && !KeyHandler.getInstance().isLeft() && !KeyHandler.getInstance().isUp()) {
            direction = Direction.DOWN_RIGHT;
        }
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
        tileY = mapTileY.get(Map.of(playerState, Direction.force(direction)));
        tileX = animationCounter.getFrame();
    }

    public void draw(Graphics2D g2d, Camera camera) {
        findTile();
        Tile tile = new Tile(playerTileSet, tileX, tileY, width, height);
        BufferedImage heroImage = tile.getImage();
        g2d.drawImage(heroImage, x - camera.getX(), y - camera.getY(), null);
        if(playerState == PlayerState.ATTACKA){
            g2d.setColor(Color.RED);
            g2d.drawRect(x+attackAHitBox.get(Direction.force(direction)).getX() - camera.getX(), y+attackAHitBox.get(Direction.force(direction)).getY() - camera.getY(), attackAHitBox.get(Direction.force(direction)).getWidth(), attackAHitBox.get(Direction.force(direction)).getHeight());
        }
    }
}

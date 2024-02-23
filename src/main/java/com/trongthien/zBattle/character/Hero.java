package com.trongthien.zBattle.character;

import com.trongthien.zBattle.key.KeyHandler;
import com.trongthien.zBattle.screen.MainPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class Hero extends Entity {
    MainPanel mainPanel;
    public KeyHandler keyHandler ;
    public int counter=0;
    public int frame=0;
    public Map<String,Integer> maxFrame;
    public int animationSpeed=3;
    public int tileX,tileY;
    Map<Map<String,String>,Integer> mapTileY;
    public Hero(int x, int y, KeyHandler keyHandler, MainPanel mainPanel) {
        this.x = x;
        this.y = y;
        width=64;
        height=64;
        speed=4;
        solidX=24;
        solidY=32;
        getSolidArea();
        this.keyHandler = keyHandler;
        this.mainPanel = mainPanel;
        state="idle";
        direction="down";
        loadFrame();
        updateTile();
    }
    public void update() {
        if (keyHandler.up) {
            y -= speed;
            checkCollision("up");
        }
        if (keyHandler.down) {
            y += speed;
            checkCollision("down");
        }
        if (keyHandler.left) {
            x -= speed;
            checkCollision("left");
        }
        if (keyHandler.right) {
            x += speed;
            checkCollision("right");
        }
        updateDirection();
        updateState();
        counter++;
        if (counter==animationSpeed) {
            counter=0;
            frame++;
            if (frame==maxFrame.get(state)) {
                frame=0;
            }
        }
        updateTile();
    }
    private void updateDirection() {
        if (keyHandler.up) {
            direction="up";
        }
        if (keyHandler.down) {
            direction="down";
        }
        if (keyHandler.left) {
            direction="left";
        }
        if (keyHandler.right) {
            direction="right";
        }
    }
    private void getSolidArea() {
        solidArea = new Rectangle(x+solidX,y+solidY,16,16);
    }
    private void updateState() {
        if (keyHandler.up||keyHandler.down||keyHandler.left||keyHandler.right) {
            state="move";
        } else {
            state="idle";
        }
    }
    private void loadFrame() {
        mapTileY = Map.of(
                Map.of("idle","down"),0,
                Map.of("idle","right"),1,
                Map.of("idle","up"),2,
                Map.of("idle","left"),3,
                Map.of("move","down"),4,
                Map.of("move","right"),5,
                Map.of("move","up"),6,
                Map.of("move","left"),7
        );
        maxFrame = Map.of(
                "idle",8,
                "move",8
        );
    }
    private void updateTile() {
        tileY = mapTileY.get(Map.of(state,direction));
        tileX=frame;
    }
    private void checkCollision(String type) {
        getSolidArea();
        boolean collisionTop =false;
        boolean collisionBottom =false;
        boolean collisionLeft =false;
        boolean collisionRight =false;
        if (mainPanel.world.isSolid(solidArea.x, solidArea.y) || mainPanel.world.isSolid(solidArea.x+solidArea.width, solidArea.y)) {
            collisionTop=true;
        }
        if ( mainPanel.world.isSolid(solidArea.x, solidArea.y+solidArea.height) || mainPanel.world.isSolid(solidArea.x+solidArea.width, solidArea.y+solidArea.height)) {
            collisionBottom=true;
        }
        if (mainPanel.world.isSolid(solidArea.x, solidArea.y) || mainPanel.world.isSolid(solidArea.x, solidArea.y+solidArea.height)) {
            collisionLeft=true;
        }
        if (mainPanel.world.isSolid(solidArea.x+solidArea.width, solidArea.y) || mainPanel.world.isSolid(solidArea.x+solidArea.width, solidArea.y+solidArea.height)) {
            collisionRight=true;
        }
        Rectangle nextSolidArea = new Rectangle(solidArea);
        if(collisionTop && type.equals("up")) {
            while (mainPanel.world.isSolid(nextSolidArea.x, nextSolidArea.y)||mainPanel.world.isSolid(nextSolidArea.x+nextSolidArea.width, nextSolidArea.y)) {
                nextSolidArea.y++;
            }
        }
        if(collisionBottom && type.equals("down")) {
            while (mainPanel.world.isSolid(nextSolidArea.x, nextSolidArea.y+nextSolidArea.height)||mainPanel.world.isSolid(nextSolidArea.x+nextSolidArea.width, nextSolidArea.y+nextSolidArea.height)) {
                nextSolidArea.y--;
            }
        }
        if(collisionLeft && type.equals("left")) {
            while (mainPanel.world.isSolid(nextSolidArea.x, nextSolidArea.y)||mainPanel.world.isSolid(nextSolidArea.x, nextSolidArea.y+nextSolidArea.height)) {
                nextSolidArea.x++;
            }
        }
        if(collisionRight && type.equals("right")) {
            while (mainPanel.world.isSolid(nextSolidArea.x+nextSolidArea.width, nextSolidArea.y)||mainPanel.world.isSolid(nextSolidArea.x+nextSolidArea.width, nextSolidArea.y+nextSolidArea.height)) {
                nextSolidArea.x--;
            }
        }
        x=nextSolidArea.x-solidX;
        y=nextSolidArea.y-solidY;
    }
    public void draw(Graphics2D g2d,int cameraX,int cameraY) throws IOException {
        BufferedImage  heroTiles = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/character/hero/hero_tiles.png")));
        BufferedImage heroImage = heroTiles.getSubimage(tileX*width, tileY*height, width, height);
        g2d.drawImage(heroImage, x-cameraX, y-cameraY, null);
    }
}

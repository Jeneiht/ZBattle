package com.trongthien.zBattle.character;

import com.trongthien.zBattle.GameMap.GameMap;
import com.trongthien.zBattle.component.CollisionChecker;

import java.awt.*;

public class Entity {
    GameMap gameMap;
    CollisionChecker collisionChecker;
    public int x, y, width, height;
    public int speed;
    public Direction direction;
    //direction: up, down, left, right

    public int solidX, solidY,solidWidth,solidHeight;
    public Rectangle solidArea=new Rectangle();
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
}

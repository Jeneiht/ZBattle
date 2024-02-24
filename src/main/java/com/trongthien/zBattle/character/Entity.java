package com.trongthien.zBattle.character;

import com.trongthien.zBattle.GameMap.World;
import com.trongthien.zBattle.component.CollisionChecker;

import java.awt.*;

public class Entity {
    World world;
    CollisionChecker collisionChecker;
    public int x, y, width, height;
    public int speed;
    public State state;
    public Direction direction;
    //direction: up, down, left, right
    //state: idle, walk, run, attackA, attackB, attackC, idleDrawn, walkDrawn, hurtDrawn, sheath
    public int solidX, solidY,solidWidth,solidHeight;
    public Rectangle solidArea=new Rectangle();
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
}

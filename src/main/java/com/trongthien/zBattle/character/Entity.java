package com.trongthien.zBattle.character;

import java.awt.*;

public class Entity {
    public int x, y, width, height;
    public int speed;
    public String state, direction;
    //direction: up, down, left, right
    //state: idle, walk, run, attackA, attackB, attackC, idleDrawn, walkDrawn, hurtDrawn, sheath
    public int solidX, solidY;
    public Rectangle solidArea=new Rectangle();
}

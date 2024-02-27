package com.trongthien.zBattle.character;

import com.trongthien.zBattle.GameMap.GameMap;
import com.trongthien.zBattle.component.CollisionChecker;
import lombok.Getter;

import java.awt.*;

@Getter
public class Entity {
    GameMap gameMap;
    CollisionChecker collisionChecker;
    public int x, y, width, height;
    public int speed;
    public Direction direction;
    //direction: up, down, left, right, up_left, up_right, down_left, down_right
    public HitBox bodyHitBox;
}

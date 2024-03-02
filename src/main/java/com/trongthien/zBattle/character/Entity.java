package com.trongthien.zBattle.character;

import Movement.Movement;
import com.trongthien.zBattle.GameMap.GameMap;
import com.trongthien.zBattle.component.CollisionChecker;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class Entity {
    Movement currentMovement;
    GameMap gameMap;
    CollisionChecker collisionChecker;
    public int x, y, width, height;
    public int speed;

    boolean changeDirection;
    public Direction direction;
    //direction: up, down, left, right, up_left, up_right, down_left, down_right
    public HitBox bodyHitBox;
}

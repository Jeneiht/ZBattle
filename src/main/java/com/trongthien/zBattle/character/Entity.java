package com.trongthien.zBattle.character;

import com.trongthien.zBattle.Movement.Movement;
import com.trongthien.zBattle.Attack.Attack;
import com.trongthien.zBattle.GameMap.Camera;
import com.trongthien.zBattle.UI.HealthBar;
import com.trongthien.zBattle.component.CollisionChecker;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public abstract class Entity {
    protected int health;
    protected HealthBar healthBar;
    protected Party party;
    protected int damage;
    protected int defense;
    protected Movement currentMovement;
    protected CollisionChecker collisionChecker;
    protected int x, y, width, height;
    protected int speed;
    protected Direction direction;
    //direction: up, down, left, right, up_left, up_right, down_left, down_right
    public abstract HitBox getHitBox();
    protected Attack currentAttack;
    protected boolean attacking;
    public abstract void draw(Graphics2D g2d, Camera camera);
    public abstract void update();
}

package com.trongthien.zbattle.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.trongthien.zbattle.controller.combat.attack.Attack;
import com.trongthien.zbattle.controller.combat.hitbox.HitBox;
import com.trongthien.zbattle.controller.movement.Direction;
import com.trongthien.zbattle.controller.movement.Movement;
import com.trongthien.zbattle.view.entity.HealthBar;
import com.trongthien.zbattle.controller.CollisionChecker;
import com.trongthien.zbattle.view.map.Camera;
import lombok.Getter;
import lombok.Setter;

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
    protected int x, y;
    protected  int width, height;
    protected int speed;
    protected Direction direction;
    //direction: up, down, left, right, up_left, up_right, down_left, down_right
    public abstract HitBox getHitBox();
    protected Attack currentAttack;
    protected boolean attacking;
    public abstract void draw(SpriteBatch spriteBatch, Camera camera);
    public abstract void update();
}

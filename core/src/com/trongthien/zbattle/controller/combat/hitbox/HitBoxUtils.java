package com.trongthien.zbattle.controller.combat.hitbox;

public class HitBoxUtils {
    //Singleton
    private static HitBoxUtils instance;

    private HitBoxUtils() {
    }

    public static HitBoxUtils getInstance() {
        if (instance == null) {
            instance = new HitBoxUtils();
        }
        return instance;
    }

    public boolean checkCollide(HitBox hitBox1, HitBox hitBox2) {
        if (hitBox1 == null || hitBox2 == null) {
            return false;
        }
        if (hitBox1.getX() >= hitBox2.getX() &&
                hitBox1.getX() <= hitBox2.getX() + hitBox2.getWidth() &&
                hitBox1.getY() >= hitBox2.getY() &&
                hitBox1.getY() <= hitBox2.getY() + hitBox2.getHeight()) {
            return true;
        }
        if (hitBox1.getX() + hitBox1.getWidth() >= hitBox2.getX() &&
                hitBox1.getX() + hitBox1.getWidth() <= hitBox2.getX() + hitBox2.getWidth() &&
                hitBox1.getY() >= hitBox2.getY() &&
                hitBox1.getY() <= hitBox2.getY() + hitBox2.getHeight()) {
            return true;
        }
        if (hitBox1.getX() >= hitBox2.getX() &&
                hitBox1.getX() <= hitBox2.getX() + hitBox2.getWidth() &&
                hitBox1.getY() + hitBox1.getHeight() >= hitBox2.getY() &&
                hitBox1.getY() + hitBox1.getHeight() <= hitBox2.getY() + hitBox2.getHeight()) {
            return true;
        }
        if (hitBox1.getX() + hitBox1.getWidth() >= hitBox2.getX() &&
                hitBox1.getX() + hitBox1.getWidth() <= hitBox2.getX() + hitBox2.getWidth() &&
                hitBox1.getY() + hitBox1.getHeight() >= hitBox2.getY() &&
                hitBox1.getY() + hitBox1.getHeight() <= hitBox2.getY() + hitBox2.getHeight()) {
            return true;
        }
        if(hitBox2.getX() >= hitBox1.getX() &&
                hitBox2.getX() <= hitBox1.getX() + hitBox1.getWidth() &&
                hitBox2.getY() >= hitBox1.getY() &&
                hitBox2.getY() <= hitBox1.getY() + hitBox1.getHeight()) {
            return true;
        }
        if(hitBox2.getX() + hitBox2.getWidth() >= hitBox1.getX() &&
                hitBox2.getX() + hitBox2.getWidth() <= hitBox1.getX() + hitBox1.getWidth() &&
                hitBox2.getY() >= hitBox1.getY() &&
                hitBox2.getY() <= hitBox1.getY() + hitBox1.getHeight()) {
            return true;
        }
        if(hitBox2.getX() >= hitBox1.getX() &&
                hitBox2.getX() <= hitBox1.getX() + hitBox1.getWidth() &&
                hitBox2.getY() + hitBox2.getHeight() >= hitBox1.getY() &&
                hitBox2.getY() + hitBox2.getHeight() <= hitBox1.getY() + hitBox1.getHeight()) {
            return true;
        }
        if(hitBox2.getX() + hitBox2.getWidth() >= hitBox1.getX() &&
                hitBox2.getX() + hitBox2.getWidth() <= hitBox1.getX() + hitBox1.getWidth() &&
                hitBox2.getY() + hitBox2.getHeight() >= hitBox1.getY() &&
                hitBox2.getY() + hitBox2.getHeight() <= hitBox1.getY() + hitBox1.getHeight()) {
            return true;
        }
        return false;
    }
}

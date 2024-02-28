package com.trongthien.zBattle.component;

import com.trongthien.zBattle.character.Entity;
import com.trongthien.zBattle.character.HitBox;

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

    public boolean isColliding(Entity entity1, Entity entity2, HitBox hitBox1, HitBox hitBox2) {
        if (entity1.getX() + hitBox1.getX() >= entity2.getX() + hitBox2.getY() &&
                entity1.getX() + hitBox1.getX() <= entity2.getX() + hitBox2.getX() + hitBox2.getWidth() &&
                entity1.getY() + hitBox1.getY() >= entity2.getY() + hitBox2.getY() &&
                entity1.getY() + hitBox1.getY() <= entity2.getY() + hitBox2.getY() + hitBox2.getHeight()) {
            return true;
        }
        if (entity1.getX() + hitBox1.getX() + hitBox1.getWidth() >= entity2.getX() + hitBox2.getX() &&
                entity1.getX() + hitBox1.getX() + hitBox1.getWidth() <= entity2.getX() + hitBox2.getX() + hitBox2.getWidth() &&
                entity1.getY() + hitBox1.getY() >= entity2.getY() + hitBox2.getY() &&
                entity1.getY() + hitBox1.getY() <= entity2.getY() + hitBox2.getY() + hitBox2.getHeight()) {
            return true;
        }
        if (entity1.getX() + hitBox1.getX() >= entity2.getX() + hitBox2.getX() &&
                entity1.getX() + hitBox1.getX() <= entity2.getX() + hitBox2.getX() + hitBox2.getWidth() &&
                entity1.getY() + hitBox1.getY() + hitBox1.getHeight() >= entity2.getY() + hitBox2.getY() &&
                entity1.getY() + hitBox1.getY() + hitBox1.getHeight() <= entity2.getY() + hitBox2.getY() + hitBox2.getHeight()) {
            return true;
        }
        if (entity1.getX() + hitBox1.getX() + hitBox1.getWidth() >= entity2.getX() + hitBox2.getX() &&
                entity1.getX() + hitBox1.getX() + hitBox1.getWidth() <= entity2.getX() + hitBox2.getX() + hitBox2.getWidth() &&
                entity1.getY() + hitBox1.getY() + hitBox1.getHeight() >= entity2.getY() + hitBox2.getY() &&
                entity1.getY() + hitBox1.getY() + hitBox1.getHeight() <= entity2.getY() + hitBox2.getY() + hitBox2.getHeight()) {
            return true;
        }
        if(entity2.getX() + hitBox2.getX() >= entity1.getX() + hitBox1.getX() &&
                entity2.getX() + hitBox2.getX() <= entity1.getX() + hitBox1.getX() + hitBox1.getWidth() &&
                entity2.getY() + hitBox2.getY() >= entity1.getY() + hitBox1.getY() &&
                entity2.getY() + hitBox2.getY() <= entity1.getY() + hitBox1.getY() + hitBox1.getHeight()){
            return true;
        }
        if(entity2.getX() + hitBox2.getX() + hitBox2.getWidth() >= entity1.getX() + hitBox1.getX() &&
                entity2.getX() + hitBox2.getX() + hitBox2.getWidth() <= entity1.getX() + hitBox1.getX() + hitBox1.getWidth() &&
                entity2.getY() + hitBox2.getY() >= entity1.getY() + hitBox1.getY() &&
                entity2.getY() + hitBox2.getY() <= entity1.getY() + hitBox1.getY() + hitBox1.getHeight()){
            return true;
        }
        if(entity2.getX() + hitBox2.getX() >= entity1.getX() + hitBox1.getX() &&
                entity2.getX() + hitBox2.getX() <= entity1.getX() + hitBox1.getX() + hitBox1.getWidth() &&
                entity2.getY() + hitBox2.getY() + hitBox2.getHeight() >= entity1.getY() + hitBox1.getY() &&
                entity2.getY() + hitBox2.getY() + hitBox2.getHeight() <= entity1.getY() + hitBox1.getY() + hitBox1.getHeight()){
            return true;
        }
        if(entity2.getX() + hitBox2.getX() + hitBox2.getWidth() >= entity1.getX() + hitBox1.getX() &&
                entity2.getX() + hitBox2.getX() + hitBox2.getWidth() <= entity1.getX() + hitBox1.getX() + hitBox1.getWidth() &&
                entity2.getY() + hitBox2.getY() + hitBox2.getHeight() >= entity1.getY() + hitBox1.getY() &&
                entity2.getY() + hitBox2.getY() + hitBox2.getHeight() <= entity1.getY() + hitBox1.getY() + hitBox1.getHeight()){
            return true;
        }
        return false;
    }
}

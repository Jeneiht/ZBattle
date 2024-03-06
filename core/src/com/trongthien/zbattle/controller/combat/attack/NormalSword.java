package com.trongthien.zbattle.controller.combat.attack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.trongthien.zbattle.common.constant.GameConstant;
import com.trongthien.zbattle.controller.combat.hitbox.HitBox;
import com.trongthien.zbattle.controller.movement.Direction;
import com.trongthien.zbattle.model.Entity;

public class NormalSword extends Attack {
    public NormalSword(Entity owner) {
        super(owner);
        //debug hitbox
    }

    @Override
    public void setSoundPath() {
        soundPath = "sound/NormalSword.wav";
    }

    @Override
    public HitBox getHitBox() {
        Direction direction = Direction.force(owner.getDirection());
        int ownerX=owner.getX();
        int ownerY=owner.getY();
        HitBox hitBox=new HitBox(0,0,0,0);
        switch (direction){
            case LEFT:
                hitBox.setX(ownerX);
                hitBox.setY(ownerY);
                hitBox.setWidth(32);
                hitBox.setHeight(48);
                break;
            case RIGHT:
                hitBox.setX(ownerX+8);
                hitBox.setY(ownerY);
                hitBox.setWidth(32);
                hitBox.setHeight(48);
                break;
        }
        return hitBox;
    }
    @Override
    protected void setDamage() {
        damage=50;
    }
    @Override
    protected void setDuration() {
        duration =500;
    }
}

package com.trongthien.zBattle.Attack;

import com.trongthien.zBattle.character.Direction;
import com.trongthien.zBattle.character.Entity;
import com.trongthien.zBattle.character.HitBox;

import java.time.Duration;

public class NormalSword extends Attack {
    public NormalSword(Entity owner) {
        super(owner);
    }
    @Override
    public HitBox getHitBox() {
        Direction direction = Direction.force(owner.getDirection());
        int ownerX=owner.getX();
        int ownerY=owner.getY();
        HitBox hitBox=new HitBox(0,0,0,0);
        switch (direction){
            case DOWN:
                hitBox.setX(ownerX);
                hitBox.setY(ownerY+24);
                hitBox.setWidth(64);
                hitBox.setHeight(40);
                break;
            case UP:
                hitBox.setX(ownerX);
                hitBox.setY(ownerY);
                hitBox.setWidth(64);
                hitBox.setHeight(40);
                break;
            case LEFT:
                hitBox.setX(ownerX);
                hitBox.setY(ownerY);
                hitBox.setWidth(40);
                hitBox.setHeight(64);
                break;
            case RIGHT:
                hitBox.setX(ownerX+24);
                hitBox.setY(ownerY);
                hitBox.setWidth(40);
                hitBox.setHeight(64);
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
        duration =1;
    }
}

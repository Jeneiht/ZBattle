package com.trongthien.zbattle.controller.combat.attack;

import com.trongthien.zbattle.controller.combat.hitbox.HitBox;
import com.trongthien.zbattle.controller.movement.Direction;
import com.trongthien.zbattle.model.Entity;

public class RatfolkAttack extends Attack{
    public RatfolkAttack(Entity owner) {
        super(owner);
    }

    @Override
    public void setSoundPath() {

    }

    @Override
    public HitBox getHitBox() {
        Direction direction = Direction.force(owner.getDirection());
        int ownerX=owner.getX();
        int ownerY=owner.getY();
        if(direction==Direction.RIGHT){
            return new HitBox(ownerX+28,ownerY,20,30);
        }
        else{
            return new HitBox(ownerX+16,ownerY,20,30);
        }
    }

    @Override
    protected void setDamage() {
        damage = 50;
    }

    @Override
    protected void setDuration() {
        duration = 500;
    }
}

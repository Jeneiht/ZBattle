package com.trongthien.zbattle.controller.combat.attack;

import com.trongthien.zbattle.controller.combat.hitbox.HitBox;
import com.trongthien.zbattle.controller.movement.Direction;
import com.trongthien.zbattle.model.Entity;

public class GhoulAttack extends Attack{
    public GhoulAttack(Entity owner) {
        super(owner);
    }

    @Override
    public void setSoundPath() {
        soundPath = "sound/GhoulAttack.ogg";
    }

    @Override
    public HitBox getHitBox() {
        Direction direction = Direction.force(owner.getDirection());
        int ownerX=owner.getX();
        int ownerY=owner.getY();
        if(direction==Direction.RIGHT){
            return new HitBox(ownerX+16,ownerY,16,32);
        }
        else{
            return new HitBox(ownerX,ownerY,16,32);
        }
    }
    @Override
    protected void setDamage() {
        damage=5;
    }

    @Override
    protected void setDuration() {
        duration =1;
    }
}

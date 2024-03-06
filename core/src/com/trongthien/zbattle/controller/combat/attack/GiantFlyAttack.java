package com.trongthien.zbattle.controller.combat.attack;

import com.trongthien.zbattle.controller.combat.hitbox.HitBox;
import com.trongthien.zbattle.model.Entity;

public class GiantFlyAttack extends Attack{
    public GiantFlyAttack(Entity owner) {
        super(owner);
    }

    @Override
    public void setSoundPath() {

    }

    @Override
    public HitBox getHitBox() {
        return owner.getHitBox();
    }

    @Override
    protected void setDamage() {
        damage = 10;
    }

    @Override
    protected void setDuration() {
        duration = 1;
    }
}

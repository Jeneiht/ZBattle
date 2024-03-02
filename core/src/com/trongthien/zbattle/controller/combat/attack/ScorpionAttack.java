package com.trongthien.zbattle.controller.combat.attack;

import com.trongthien.zbattle.controller.combat.hitbox.HitBox;
import com.trongthien.zbattle.model.Entity;

public class ScorpionAttack extends Attack{
    public ScorpionAttack(Entity owner) {
        super(owner);
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

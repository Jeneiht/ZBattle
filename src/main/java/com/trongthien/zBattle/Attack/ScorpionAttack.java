package com.trongthien.zBattle.Attack;

import com.trongthien.zBattle.character.Entity;
import com.trongthien.zBattle.character.HitBox;

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

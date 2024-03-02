package com.trongthien.zBattle.Attack;

import com.trongthien.zBattle.character.Entity;
import com.trongthien.zBattle.character.HitBox;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public abstract class Attack {
    protected Entity owner;
    protected int damage;
    protected long duration;
    protected long timeToLive;
    private long startTime;
    ArrayList<Entity> hitEntities;
    public Attack(Entity owner) {
        this.owner = owner;
        hitEntities = new ArrayList<>();
        setDamage();
        setDuration();
        startTime = System.currentTimeMillis();
        timeToLive = duration;
    }
    public void addHitEntity(Entity entity){
        hitEntities.add(entity);
    }
    public boolean isNotHit(Entity entity){
        return !hitEntities.contains(entity);
    }
    public void update(){
        long currentTime = System.currentTimeMillis();
        timeToLive -= currentTime-startTime;
        startTime = currentTime;
    }
    public abstract HitBox getHitBox();
    protected abstract void setDamage() ;
    protected abstract void setDuration();
}

package com.trongthien.zbattle.controller.combat.attack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.trongthien.zbattle.controller.combat.hitbox.HitBox;
import com.trongthien.zbattle.model.Entity;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public abstract class Attack {
    protected String soundPath;
    protected Entity owner;
    protected int damage;
    protected long duration;
    protected long timeToLive;
    private long startTime;
    ArrayList<Entity> hitEntities;
    public Attack(Entity owner) {
        setSoundPath();
        this.owner = owner;
        hitEntities = new ArrayList<>();
        setDamage();
        setDuration();
        startTime = System.currentTimeMillis();
        timeToLive = duration;
        playSound();
    }
    private void playSound(){
        try {
            Music music = Gdx.audio.newMusic(Gdx.files.internal(soundPath));
            music.setLooping(false);
            music.setVolume(0.3f);
            music.play();
        }catch (Exception e){
            return;
        }
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
    public abstract void setSoundPath();
    public abstract HitBox getHitBox();
    protected abstract void setDamage() ;
    protected abstract void setDuration();
}

package com.trongthien.zBattle.GameMap;

import com.trongthien.zBattle.character.Hero;
import com.trongthien.zBattle.constant.GameConstant;

public class Camera {
    public Hero hero;
    public World world;
    public int x, y;
    public Camera(Hero hero, World world) {
        this.hero = hero;
        this.world = world;
        update();
    }
    public void update() {
        x=hero.x+ GameConstant.tileSize/2-GameConstant.screenWidth/2;
        y=hero.y+GameConstant.tileSize/2-GameConstant.screenHeight/2;
        if(x<0) x=0;
        if(y<0) y=0;
        if(x+ GameConstant.screenWidth>world.worldWidth){
            x=world.worldWidth- GameConstant.screenWidth;
        }
        if(y+ GameConstant.screenHeight>world.worldHeight){
            y=world.worldHeight- GameConstant.screenHeight;
        }
    }
}

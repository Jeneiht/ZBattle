package com.trongthien.zbattle.controller.movement.impl;

import com.trongthien.zbattle.controller.movement.Movement;
import com.trongthien.zbattle.model.Entity;

public class NoMovement implements Movement {
    //Singleton
    private static NoMovement instance;
    private NoMovement(){}
    public static NoMovement getInstance(){
        if(instance==null){
            instance = new NoMovement();
        }
        return instance;
    }
    @Override
    public void move(Entity entity) {
        //do nothing
    }
}

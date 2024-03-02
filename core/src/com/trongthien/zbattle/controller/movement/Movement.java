package com.trongthien.zbattle.controller.movement;

import com.trongthien.zbattle.model.Entity;

public interface Movement {
    //Strategy pattern
    void move(Entity entity);
}

package com.trongthien.zbattle.controller.combat.hitbox;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HitBox {
    private float x;
    private float y;
    private int width;
    private int height;

    public HitBox(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


}

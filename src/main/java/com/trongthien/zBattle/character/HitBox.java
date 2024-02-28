package com.trongthien.zBattle.character;

import lombok.Getter;

@Getter
public class HitBox {
    private int x;
    private int y;
    private int width;
    private int height;

    public HitBox(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


}

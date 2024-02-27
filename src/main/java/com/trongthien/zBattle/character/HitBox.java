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

    public boolean isColliding(HitBox other) {
        if(x>= other.x && x <= other.x + other.width && y >= other.y && y <= other.y + other.height) {
            return true;
        }
        if(x + width >= other.x && x + width <= other.x + other.width && y >= other.y && y <= other.y + other.height) {
            return true;
        }
        if(x >= other.x && x <= other.x + other.width && y + height >= other.y && y + height <= other.y + other.height) {
            return true;
        }
        if(x + width >= other.x && x + width <= other.x + other.width && y + height >= other.y && y + height <= other.y + other.height) {
            return true;
        }
        return false;
    }
}

package com.trongthien.zBattle.character;

public enum Direction {
    UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT;
    public static Direction force(Direction direction) {
        switch (direction) {
            case UP_LEFT:
                return LEFT;
            case UP_RIGHT:
                return RIGHT;
            case DOWN_LEFT:
                return LEFT;
            case DOWN_RIGHT:
                return RIGHT;
            default:
                return direction;
        }
    }
}

package com.trongthien.zbattle.controller.movement;

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
    public static Direction getRandom() {
        int random = (int) (Math.random() * 8);
        switch (random) {
            case 0:
                return UP;
            case 1:
                return DOWN;
            case 2:
                return LEFT;
            case 3:
                return RIGHT;
            case 4:
                return UP_LEFT;
            case 5:
                return UP_RIGHT;
            case 6:
                return DOWN_LEFT;
            case 7:
                return DOWN_RIGHT;
            default:
                return UP;
        }
    }
}

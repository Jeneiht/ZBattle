package com.trongthien.zBattle.component;


import lombok.Getter;

@Getter
public class AnimationCounter {
    public int counter;
    public int frame;
    public int maxFrame;
    public int animationSpeed;

    public AnimationCounter(int animationSpeed) {
        counter = 0;
        frame = 0;
        maxFrame=0;
        this.animationSpeed = animationSpeed;
    }

    public void start(int maxFrame) {
        frame = 0;
        this.maxFrame = maxFrame;
        counter = 0;
    }

    public void update() {
        counter++;
        if (counter >= animationSpeed) {
            counter = 0;
            frame++;
            if (frame >= maxFrame) {
                frame = 0;
            }
        }
    }
    public boolean isFinished() {
        return frame == maxFrame - 1;
    }
    public boolean isStarting() {
        return frame == 0&&counter==0;
    }
}

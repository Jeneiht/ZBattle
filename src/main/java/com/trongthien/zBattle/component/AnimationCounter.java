package com.trongthien.zBattle.component;


import lombok.Getter;

@Getter
public class AnimationCounter {
    public int counter;
    public int frame;
    public int maxFrame;
    public int animationSpeed;
    public boolean blocked = false;

    public AnimationCounter(int animationSpeed) {
        counter = 0;
        frame = 0;
        this.animationSpeed = animationSpeed;
    }

    public void start(int maxFrame, boolean blocked) {
        if (this.blocked && !isEndAnimation()) {
            update();
            return;
        }
        frame = 0;
        this.maxFrame = maxFrame;
        this.blocked = blocked;
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

    public boolean isEndAnimation() {
        return frame == maxFrame - 1;
    }

    public boolean isBlocked() {
        return blocked;
    }
}

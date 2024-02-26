package com.trongthien.zBattle.component;


import lombok.Getter;

public class AnimationUtils {
    //Singleton
    public static AnimationUtils animationUtils;
    public int counter;
    @Getter
    public int frame;
    public int maxFrame;
    public int animationSpeed;
    public boolean blocked = false;

    private AnimationUtils() {
    }

    public static AnimationUtils getInstance() {
        if (animationUtils == null) {
            animationUtils = new AnimationUtils();
        }
        return animationUtils;
    }

    public void startAnimation(int animationSpeed, int maxFrame, boolean blocked) {
        if (this.blocked && !endAnimation()) {
            update();
            return;
        }
        frame = 0;
        this.animationSpeed = animationSpeed;
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

    public boolean endAnimation() {
        return frame == maxFrame - 1;
    }

    public boolean isBlocked() {
        return blocked;
    }
}

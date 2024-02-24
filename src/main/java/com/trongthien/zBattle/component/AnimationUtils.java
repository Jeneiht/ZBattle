package com.trongthien.zBattle.component;


public class AnimationUtils {
    public int counter;
    public int frame;
    public int animationSpeed;

    public AnimationUtils(int animationSpeed) {
        counter=0;
        frame=0;
        this.animationSpeed = animationSpeed;
    }
    public void update(int maxFrame) {
        counter++;
        if (counter > animationSpeed) {
            counter = 0;
            frame++;
            frame%=maxFrame;
        }
    }
    public int getFrame() {
        return frame;
    }
}

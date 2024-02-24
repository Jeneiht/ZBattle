package com.trongthien.zBattle.key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    //Singleton

    public static KeyHandler keyHandler;
    private boolean up, down, left, right,run, attack, enter, esc;

    private KeyHandler() {
    }

    public static KeyHandler getInstance(){
        if(keyHandler==null){
            keyHandler = new KeyHandler();
        }
        return keyHandler;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code==KeyEvent.VK_SHIFT){
            run=true;
        }
        if (code == KeyEvent.VK_W) {
            up = true;
        }
        if (code == KeyEvent.VK_S) {
            down = true;
        }
        if (code == KeyEvent.VK_A) {
            left = true;
        }
        if (code == KeyEvent.VK_D) {
            right = true;
        }
        if (code == KeyEvent.VK_SPACE) {
            attack = true;
        }
        if (code == KeyEvent.VK_ENTER) {
            enter = true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            esc = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code==KeyEvent.VK_SHIFT){
            run=false;
        }
        if (code == KeyEvent.VK_W) {
            up = false;
        }
        if (code == KeyEvent.VK_S) {
            down = false;
        }
        if (code == KeyEvent.VK_A) {
            left = false;
        }
        if (code == KeyEvent.VK_D) {
            right = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            attack = false;
        }
        if (code == KeyEvent.VK_ENTER) {
            enter = false;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            esc = false;
        }

    }
    public boolean isUp() {
        return up;
    }
    public boolean isDown() {
        return down;
    }
    public boolean isLeft() {
        return left;
    }
    public boolean isRight() {
        return right;
    }
    public boolean isRun() {
        return run;
    }
    public boolean isAttack() {
        return attack;
    }
    public boolean isEnter() {
        return enter;
    }
    public boolean isEsc() {
        return esc;
    }

}

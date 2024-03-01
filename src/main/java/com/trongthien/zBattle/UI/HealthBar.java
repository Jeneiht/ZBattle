package com.trongthien.zBattle.UI;

import com.trongthien.zBattle.character.Entity;

import javax.swing.*;
import java.awt.*;

public class HealthBar extends JProgressBar {
    private int maxHealth;
    private int currentHealth;
    Entity entity;
    public HealthBar(Entity entity) {
        this.entity = entity;
        this.maxHealth = entity.getHealth();
        this.currentHealth = entity.getHealth();
    }
    public void update(){
        this.currentHealth = entity.getHealth();
    }
    public void draw(Graphics2D g2d,int x, int y, int width, int height){
        g2d.setColor(Color.RED);
        g2d.fillRect(x, y, (int)((double)width*currentHealth/maxHealth), height);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, width, height);
    }
}

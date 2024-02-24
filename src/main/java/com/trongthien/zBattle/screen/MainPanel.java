package com.trongthien.zBattle.screen;

import com.trongthien.zBattle.GameMap.Camera;
import com.trongthien.zBattle.GameMap.World;
import com.trongthien.zBattle.constant.GameConstant;
import com.trongthien.zBattle.character.Hero;
import com.trongthien.zBattle.key.KeyHandler;


import javax.swing.*;
import java.awt.*;


public class MainPanel extends JPanel implements Runnable {
    Thread thread;
    public World world = new World();
    Hero hero = new Hero(world,world.spawnX,world.spawnY);
    Camera camera = new Camera(hero, world);

    public MainPanel() {
        this.setPreferredSize(new Dimension(GameConstant.screenWidth, GameConstant.screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyHandler.getInstance());
        this.setFocusable(true);
        world.loadMap();
    }


    public void startGameThread() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / GameConstant.FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (true) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    private void update() {
        hero.update();
        camera.update();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        world.draw(g2d, camera);
        hero.draw(g2d, camera);
        g2d.dispose();
    }
}

package com.trongthien.zBattle.screen;

import com.trongthien.zBattle.GameMap.Camera;
import com.trongthien.zBattle.GameMap.World;
import com.trongthien.zBattle.constant.GameConstant;
import com.trongthien.zBattle.character.Hero;
import com.trongthien.zBattle.key.KeyHandler;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainPanel extends JPanel implements Runnable {



    Thread thread;
    KeyHandler keyHandler = new KeyHandler();
    public World world = new World(this);
    Hero hero = new Hero(GameConstant.tileSize,GameConstant.tileSize , keyHandler, this);
    Camera camera = new Camera(hero, world);


    public MainPanel() throws IOException {
        this.setPreferredSize(new Dimension(GameConstant.screenWidth, GameConstant.screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        world.loadWorld();
    }



    public void startGameThread() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        double targetFPS = 60.0;
        double nsPerTick = 1000000000.0 / targetFPS;
        double nextDrawTime = System.nanoTime() + nsPerTick;

        while (thread != null) {
            update();
            repaint();

            try {
                double remainTime = nextDrawTime - System.nanoTime();
                if (remainTime < 0) {
                    remainTime = 0;
                }
                if (remainTime > 0) {
                    Thread.sleep((long) (remainTime / 1000000));
                }
                nextDrawTime += nsPerTick;
            } catch (InterruptedException e) {
                e.printStackTrace();
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
        try {
            world.draw(g2d, camera.x, camera.y);
            hero.draw(g2d,camera.x,camera.y);
            g2d.dispose();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

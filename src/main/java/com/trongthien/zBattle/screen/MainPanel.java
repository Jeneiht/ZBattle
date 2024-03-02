package com.trongthien.zBattle.screen;

import com.trongthien.zBattle.GameMap.Camera;
import com.trongthien.zBattle.GameMap.World;
import com.trongthien.zBattle.character.Hero;
import com.trongthien.zBattle.component.SharedContext;
import com.trongthien.zBattle.constant.GameConstant;
import com.trongthien.zBattle.key.KeyHandler;

import javax.swing.*;
import java.awt.*;


public class MainPanel extends JPanel implements Runnable {
    Thread thread;
    public Camera camera;

    public MainPanel() {
        this.setPreferredSize(new Dimension(GameConstant.screenWidth, GameConstant.screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyHandler.getInstance());
        this.setFocusable(true);
        SharedContext.getInstance().setCurrentPlayer(new Hero());
        SharedContext.getInstance().setCurrentGameMap(new World());
        camera = new Camera(SharedContext.getInstance().getCurrentPlayer(), SharedContext.getInstance().getCurrentGameMap());
    }

    public void startGameThread() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / GameConstant.FPS;
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
        SharedContext.getInstance().getCurrentGameMap().update();
        camera.update();
    }

    @Override
    protected void paintComponent(Graphics g) {
        long startTime = System.nanoTime();
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        SharedContext.getInstance().getCurrentGameMap().draw(g2d, camera);

        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1000000;
        if (duration <= 0) {
            duration = 1.0E-5;
        }
        g2d.setColor(Color.white);
        g2d.drawString("FPS: " + Math.round(1000/duration), 10, 10);
        g2d.dispose();
    }
}

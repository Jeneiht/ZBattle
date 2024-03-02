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
        super.paintComponent(g);
        long startTime = System.nanoTime();
        Graphics2D g2d = (Graphics2D) g;
        //Double buffering
        g2d.setBackground(Color.BLACK);
        g2d.fillRect(0, 0, GameConstant.screenWidth, GameConstant.screenHeight);
        SharedContext.getInstance().getCurrentGameMap().draw(g2d, camera);
        long endTime = System.nanoTime();
        //syncFPS(endTime, startTime);
        g2d.setColor(Color.WHITE);
        g2d.drawString("FPS: " + (1000000000 / (System.nanoTime() - startTime)), 10, 10);


    }

    private static void syncFPS(long endTime, long startTime) {
        long timeElapsed = endTime - startTime;
        long sleepTime = 1000000000 / GameConstant.FPS - timeElapsed;
        if (sleepTime > 0) {
            try {
                Thread.sleep(sleepTime / 1000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

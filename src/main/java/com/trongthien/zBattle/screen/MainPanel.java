package com.trongthien.zBattle.screen;

import com.trongthien.zBattle.GameMap.Camera;
import com.trongthien.zBattle.GameMap.GameMap;
import com.trongthien.zBattle.GameMap.World;
import com.trongthien.zBattle.character.Hero;
import com.trongthien.zBattle.component.SharedCurrentContext;
import com.trongthien.zBattle.constant.GameConstant;
import com.trongthien.zBattle.character.Player;
import com.trongthien.zBattle.key.KeyHandler;


import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;


public class MainPanel extends JPanel implements Runnable {
    Thread thread;
    public Camera camera;

    public MainPanel() {
        this.setPreferredSize(new Dimension(GameConstant.screenWidth, GameConstant.screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyHandler.getInstance());
        this.setFocusable(true);
        SharedCurrentContext.getInstance().setCurrentPlayer(new Hero());
        SharedCurrentContext.getInstance().setCurrentGameMap(new World());
        camera = new Camera(SharedCurrentContext.getInstance().getCurrentPlayer(), SharedCurrentContext.getInstance().getCurrentGameMap());
    }

    public void startGameThread() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
//        double drawInterval = 1000000000.0 / GameConstant.FPS;
//        double delta = 0;
//        long lastTime = System.nanoTime();
//        long currentTime;
//        while (true) {
//            currentTime = System.nanoTime();
//            delta += (currentTime - lastTime) / drawInterval;
//            lastTime = currentTime;
//            if (delta >= 1) {
//                update();
//                repaint();
//                delta--;
//            }
//        }

        //use timer
        Timer timer = new Timer(1 / (GameConstant.FPS*100), e -> {
            update();
            repaint();
        });
        timer.start();

    }

    private void update() {
        SharedCurrentContext.getInstance().getCurrentGameMap().update();
        camera.update();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        SharedCurrentContext.getInstance().getCurrentGameMap().draw(g2d, camera);
        g2d.dispose();
    }
}

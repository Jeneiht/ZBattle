package com.trongthien.zBattle.screen;

import com.trongthien.zBattle.component.ImageComponent;
import com.trongthien.zBattle.constant.GameConstant;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainPanel extends JPanel implements Runnable {



    Thread thread;
    List<List<ImageComponent>> map = new ArrayList<>();


    public MainPanel() {
        this.setPreferredSize(new Dimension(GameConstant.screenWidth, GameConstant.screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        ImageComponent imageComponent = new ImageComponent("src/main/resources/tile000.png");
        for (int i = 0; i < GameConstant.maxScreenRow; i++) {
            List<ImageComponent> row = new ArrayList<>();
            for (int j = 0; j < GameConstant.maxScreenCol; j++) {
                row.add(imageComponent);
            }
            map.add(row);
        }
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

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < GameConstant.maxScreenRow; i++) {
            for (int j = 0; j < GameConstant.maxScreenCol; j++) {
                map.get(i).get(j).draw((Graphics2D) g, j * GameConstant.tileSize, i * GameConstant.tileSize);
            }
        }

    }
}

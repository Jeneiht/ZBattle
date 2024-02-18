package com.trongthien.zBattle.component;

import com.trongthien.zBattle.constant.GameConstant;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageComponent extends JPanel {

    private BufferedImage image;

    public ImageComponent(String pathName) {
        try {
            image = ImageIO.read(new File(pathName));
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void draw(Graphics2D g2d, int x, int y) {
        g2d.setColor(Color.WHITE);
        g2d.drawImage(image, x, y, GameConstant.tileSize, GameConstant.tileSize, null);
    }


}
package com.trongthien.zBattle.component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class ResourceLoader {
    //singleton
    public static ResourceLoader resourceLoader;
    private ResourceLoader() {
    }
    public static ResourceLoader getInstance() {
        if (resourceLoader == null) {
            resourceLoader = new ResourceLoader();
        }
        return resourceLoader;
    }
    public InputStream loadInputStream(String path) {
        try {
            return getClass().getResourceAsStream(path);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(getClass().getResourceAsStream(path));
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

package com.trongthien.zBattle.component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ResourceLoader {
    //singleton
    private static ResourceLoader resourceLoader;

    // Cache for preloaded resources
    private Map<String, BufferedImage> imageCache = new HashMap<>();

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
            throw new RuntimeException("Failed to load resource: " + path, e);
        }
    }

    public BufferedImage loadImage(String path) {
        // Check if the image is in the cache
        if (imageCache.containsKey(path)) {
            return imageCache.get(path);
        }

        // If not in the cache, load the image
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream(path));
            imageCache.put(path, image); // Add the image to the cache
            return image;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load image: " + path, e);
        }
    }

    // Method to preload all resources
    public void preloadResources(String[] paths) {
        for (String path : paths) {
            loadImage(path);
        }
    }
}
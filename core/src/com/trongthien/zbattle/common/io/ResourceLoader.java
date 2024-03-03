package com.trongthien.zbattle.common.io;

import com.badlogic.gdx.graphics.Texture;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ResourceLoader {
    //singleton
    private static ResourceLoader resourceLoader;

    // Cache for preloaded resources
    private Map<String, Texture> imageCache = new HashMap<>();

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

    public Texture loadImage(String path) {
        Texture image = imageCache.get(path);
        if (image == null) {
            image = new Texture(path);
            imageCache.put(path, image);
        }
        return image;
    }
}
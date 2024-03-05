package com.trongthien.zbattle.view.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.trongthien.zbattle.common.SharedContext;
import com.trongthien.zbattle.common.constant.GameConstant;
import com.trongthien.zbattle.model.Entity;

public class HealthBar {
    private final int maxHealth;
    private int currentHealth;
    private final Entity entity;

    public HealthBar(Entity entity) {
        this.entity = entity;
        this.maxHealth = entity.getHealth();
        this.currentHealth = entity.getHealth();
    }

    public void update() {
        this.currentHealth = entity.getHealth();
    }

    public void draw(SpriteBatch batch, float x, float y, float width, float height) {
        this.update();
        Pixmap pixmap = new Pixmap((int) width, (int) height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.drawRectangle(0, 0, (int) width, (int) height);
        Texture texture = new Texture(pixmap);
        batch.draw(texture, x, y);
        pixmap.setColor(Color.RED);
        pixmap.fillRectangle(0, 0, (int) (width * currentHealth / maxHealth), (int) height);
        texture.dispose();
        texture = new Texture(pixmap);
        batch.draw(texture, x, y);
        pixmap.dispose();
    }
}
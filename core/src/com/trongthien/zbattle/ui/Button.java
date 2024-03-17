package com.trongthien.zbattle.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Button {
    private int x, y, width, height;
    private String text;
    private boolean isHovered, isClicked;
    public Button(int x, int y, int width, int height, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
    }
    public void update() {
        int mouseX = Gdx.input.getX();
        int mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
        if (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height) {
            isHovered = true;
        } else {
            isHovered = false;
        }
        if (isHovered && Gdx.input.isButtonPressed(0)) {
            isClicked = true;
        } else {
            isClicked = false;
        }
    }
    public void draw(SpriteBatch batch, BitmapFont font) {
        if (isHovered) {
            font.setColor(Color.RED);
        } else {
            font.setColor(Color.WHITE);
        }
        font.draw(batch, text, x, y + height);
    }
}

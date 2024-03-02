package com.trongthien.zbattle.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.trongthien.zbattle.common.constant.GameConstant;

public class CustomDrawer extends SpriteBatch {
    @Override
    public void draw(TextureRegion textureRegion, float x, float y) {
        y = GameConstant.screenHeight - y - textureRegion.getRegionHeight();
        super.draw(textureRegion, x, y);
    }
}

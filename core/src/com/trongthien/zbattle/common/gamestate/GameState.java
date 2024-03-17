package com.trongthien.zbattle.common.gamestate;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface GameState {
    void init();
    void update();
    void draw();
    void dispose();
}

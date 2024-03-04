package com.trongthien.zbattle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.trongthien.zbattle.common.constant.GameConstant;
import com.trongthien.zbattle.model.Hero2;
import com.trongthien.zbattle.view.CustomDrawer;
import com.trongthien.zbattle.view.map.Camera;
import com.trongthien.zbattle.view.map.impl.ForestMap;
import com.trongthien.zbattle.model.Hero;
import com.trongthien.zbattle.view.FrameRate;
import com.trongthien.zbattle.common.SharedContext;
import com.trongthien.zbattle.common.io.KeyHandler;

public class MainGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    Camera camera;
    FrameRate frameRate;

    @Override
    public void create() {
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(KeyHandler.getInstance());
        SharedContext.getInstance().setCurrentPlayer(new Hero2());
        SharedContext.getInstance().setCurrentGameMap(new ForestMap());
        frameRate = new FrameRate();
        frameRate.update();
        camera = new Camera(SharedContext.getInstance().getCurrentPlayer(), SharedContext.getInstance().getCurrentGameMap());
    }

    //Main loop
    @Override
    public void render() {
        batch.begin();
        update();
        SharedContext.getInstance().getCurrentGameMap().draw(batch, camera);
        frameRate.render();
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        frameRate.dispose();
    }

    private void update() {
        frameRate.update();
        SharedContext.getInstance().getCurrentGameMap().update();
        camera.update();
    }
}

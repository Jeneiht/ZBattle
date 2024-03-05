package com.trongthien.zbattle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.trongthien.zbattle.common.constant.GameConstant;
import com.trongthien.zbattle.model.Hero2;
import com.trongthien.zbattle.view.map.Camera;
import com.trongthien.zbattle.view.map.GameMap;
import com.trongthien.zbattle.view.map.impl.ForestMap;
import com.trongthien.zbattle.view.FrameRate;
import com.trongthien.zbattle.common.SharedContext;
import com.trongthien.zbattle.common.io.KeyHandler;

public class MainGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    OrthographicCamera camera;
    FrameRate frameRate;

    @Override
    public void create() {
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(KeyHandler.getInstance());
        SharedContext.getInstance().setCurrentPlayer(new Hero2());
        SharedContext.getInstance().setCurrentGameMap(new ForestMap());
        frameRate = new FrameRate();
        frameRate.update();
        camera = new OrthographicCamera(GameConstant.screenWidth, GameConstant.screenHeight);
        camera.position.set(GameConstant.screenWidth*GameConstant.scale / 2f, GameConstant.screenHeight*GameConstant.scale / 2f, 0);
        camera.update();
        playBackgroundMusic();
    }

    private void playBackgroundMusic() {
        Music music = Gdx.audio.newMusic(Gdx.files.internal(GameConstant.backgroundMusicPath));
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();

    }

    private void updateCamera() {
        float centerX = SharedContext.getInstance().getCurrentPlayer().getX() + (float)SharedContext.getInstance().getCurrentPlayer().getWidth() / 2;
        float centerY = SharedContext.getInstance().getCurrentPlayer().getY() + (float)SharedContext.getInstance().getCurrentPlayer().getHeight() / 2;
        GameMap gameMap = SharedContext.getInstance().getCurrentGameMap();
        if (centerX + GameConstant.screenWidth * GameConstant.scale / 2 > gameMap.getWidth()) {
            centerX = gameMap.getWidth() - GameConstant.screenWidth * GameConstant.scale / 2;
        } else if (centerX - GameConstant.screenWidth * GameConstant.scale / 2 < 0) {
            centerX = GameConstant.screenWidth * GameConstant.scale / 2;
        }
        if (centerY + GameConstant.screenHeight * GameConstant.scale / 2 > gameMap.getHeight()) {
            centerY = gameMap.getHeight() - GameConstant.screenHeight * GameConstant.scale / 2;
        } else if (centerY - GameConstant.screenHeight * GameConstant.scale / 2 < 0) {
            centerY = GameConstant.screenHeight * GameConstant.scale / 2;
        }
        camera.position.set(centerX, centerY, 0);
        camera.update();


    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, GameConstant.scale * width, GameConstant.scale * height);
        camera.update();
    }

    @Override
    public void render() {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        update();
        SharedContext.getInstance().getCurrentGameMap().draw(batch);
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
        updateCamera();
    }
}

package com.trongthien.zbattle.common.gamestate.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.trongthien.zbattle.common.SharedContext;
import com.trongthien.zbattle.common.constant.GameConstant;
import com.trongthien.zbattle.common.gamestate.GameState;
import com.trongthien.zbattle.common.gamestate.GameStateManager;
import com.trongthien.zbattle.view.FrameRate;
import com.trongthien.zbattle.view.map.GameMap;

public class PlayGameState implements GameState {
    //Singleton
    private static PlayGameState instance;
    private PlayGameState() {
    }
    public static PlayGameState getInstance() {
        if (instance == null) {
            instance = new PlayGameState();
        }
        return instance;
    }
    SpriteBatch batch;
    FrameRate frameRate;
    @Override
    public void init() {
        batch = new SpriteBatch();
        SharedContext.getInstance().setCamera(new OrthographicCamera(GameConstant.screenWidth, GameConstant.screenHeight));
        SharedContext.getInstance().getCamera().position.set(GameConstant.screenWidth*GameConstant.scale / 2f, GameConstant.screenHeight*GameConstant.scale / 2f, 0);
        SharedContext.getInstance().getCamera().update();
        frameRate = new FrameRate();
        frameRate.update();
    }

    @Override
    public void update() {
        frameRate.update();
        SharedContext.getInstance().getCurrentGameMap().update();
        updateCamera();
        if(SharedContext.getInstance().getCurrentPlayer().isDead()){
            GameStateManager.getInstance().setCurrentGameState(EndGameState.getInstance());
            GameStateManager.getInstance().getCurrentGameState().init();
        }
    }

    @Override
    public void draw() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        batch.setProjectionMatrix(SharedContext.getInstance().getCamera().combined);
        batch.begin();
        SharedContext.getInstance().getCurrentGameMap().draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        frameRate.dispose();
        batch.dispose();
    }
    private void updateCamera() {
        SharedContext.getInstance().getCamera().setToOrtho(false, GameConstant.scale * GameConstant.screenWidth, GameConstant.scale * GameConstant.screenHeight);
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
        SharedContext.getInstance().getCamera().position.set(centerX, centerY, 0);
        SharedContext.getInstance().getCamera().update();
    }

}

package com.trongthien.zbattle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.trongthien.zbattle.common.constant.GameConstant;
import com.trongthien.zbattle.common.gamestate.GameStateManager;
import com.trongthien.zbattle.common.gamestate.impl.EndGameState;
import com.trongthien.zbattle.common.gamestate.impl.MenuGameState;
import com.trongthien.zbattle.common.gamestate.impl.PlayGameState;
import com.trongthien.zbattle.model.impl.Hero2;
import com.trongthien.zbattle.view.map.GameMap;
import com.trongthien.zbattle.view.map.impl.ForestMap;
import com.trongthien.zbattle.view.FrameRate;
import com.trongthien.zbattle.common.SharedContext;
import com.trongthien.zbattle.common.io.KeyHandler;

public class MainGame extends ApplicationAdapter {

    @Override
    public void create() {
        Gdx.input.setInputProcessor(KeyHandler.getInstance());
        SharedContext.getInstance().setCurrentPlayer(new Hero2());
        SharedContext.getInstance().setCurrentGameMap(new ForestMap());
        GameStateManager.getInstance().setCurrentGameState(EndGameState.getInstance());
        GameStateManager.getInstance().getCurrentGameState().init();
        playBackgroundMusic();
    }

    private void playBackgroundMusic() {
        Music music = Gdx.audio.newMusic(Gdx.files.internal(GameConstant.backgroundMusicPath));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
    }


    @Override
    public void render() {
        GameStateManager.getInstance().getCurrentGameState().update();
        GameStateManager.getInstance().getCurrentGameState().draw();
    }

    @Override
    public void dispose() {
        GameStateManager.getInstance().getCurrentGameState().dispose();
    }
}


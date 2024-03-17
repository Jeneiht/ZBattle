package com.trongthien.zbattle.common.gamestate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class GameStateManager {
    private static GameStateManager instance;
    private GameState currentGameState;

    private GameStateManager() {
    }

    public static GameStateManager getInstance() {
        if (instance == null) {
            instance = new GameStateManager();
        }
        return instance;
    }
    private void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
    public void setCurrentGameState(GameState currentGameState) {
        if(this.currentGameState != null)
            this.currentGameState.dispose();
        clearScreen();
        this.currentGameState = currentGameState;
    }

    public GameState getCurrentGameState() {
        return currentGameState;
    }
}

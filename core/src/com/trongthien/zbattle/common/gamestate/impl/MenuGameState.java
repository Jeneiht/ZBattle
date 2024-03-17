package com.trongthien.zbattle.common.gamestate.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.trongthien.zbattle.common.constant.GameConstant;
import com.trongthien.zbattle.common.gamestate.GameState;
import com.trongthien.zbattle.common.gamestate.GameStateManager;
import com.trongthien.zbattle.ui.Button;


public class MenuGameState implements GameState {
    //Singleton
    private static MenuGameState instance;
    private MenuGameState() {
    }
    public static MenuGameState getInstance() {
        if (instance == null) {
            instance = new MenuGameState();
        }
        return instance;
    }
    private SpriteBatch batch;
    private BitmapFont font;
    private BitmapFont titleFont;
    private String title = "ZBattle";
    private String[] menuItems = new String[]{"Start", "Options", "Settings", "Exit"};
    private Button[] menuButtons = new Button[menuItems.length];
    private int currentSelection;

    @Override
    public void init() {
        batch = new SpriteBatch();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Exo-Bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 60;
        parameter.color = Color.WHITE;
        titleFont = generator.generateFont(parameter);
        generator.dispose();
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Exo-SemiBold.ttf"));
        parameter.size = 30;
        parameter.color = Color.WHITE;
        font = generator.generateFont(parameter);
        generator.dispose();
        for (int i = 0; i < menuItems.length; i++) {
            GlyphLayout layout = new GlyphLayout();
            layout.setText(font, menuItems[i]);
            menuButtons[i] = new Button((int) GameConstant.screenWidth / 2 - (int) layout.width / 2, (int) GameConstant.screenHeight - 200 - i * 75, (int) layout.width, (int) layout.height, menuItems[i]);
        }
    }

    @Override
    public void update() {
        for (Button menuButton : menuButtons) {
            menuButton.update();
            if (menuButton.isClicked()) {
                menuButton.setClicked(false);
                switch (menuButton.getText()) {
                    case "Start":
                        GameStateManager.getInstance().setCurrentGameState(PlayGameState.getInstance());
                        GameStateManager.getInstance().getCurrentGameState().init();
                        break;
                    case "Options":
                        break;
                    case "Settings":
                        break;
                    case "Exit":
                        Gdx.app.exit();
                        break;
                }
            }
        }
    }


    @Override
    public void draw() {
        GlyphLayout layout = new GlyphLayout();
        //draw title
        layout.setText(titleFont, title);
        batch.begin();
        titleFont.draw(batch, title, (float) GameConstant.screenWidth / 2 - layout.width / 2, (float) GameConstant.screenHeight - 100);
        //draw menu buttons
        for (Button menuButton : menuButtons) {
            menuButton.draw(batch, font);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        titleFont.dispose();
    }
}

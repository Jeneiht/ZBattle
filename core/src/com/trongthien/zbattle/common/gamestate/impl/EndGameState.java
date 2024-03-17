package com.trongthien.zbattle.common.gamestate.impl;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.trongthien.zbattle.common.constant.GameConstant;
import com.trongthien.zbattle.common.gamestate.*;
import com.trongthien.zbattle.ui.Button;

public class EndGameState implements GameState{
    //Singleton
    private static EndGameState instance;
    private EndGameState() {
    }
    public static EndGameState getInstance() {
        if (instance == null) {
            instance = new EndGameState();
        }
        return instance;
    }

    private BitmapFont font;
    private BitmapFont titleFont;
    SpriteBatch batch;
    private String title = "Game Over";
    private String[] buttonTexts = new String[]{"Back to Menu"};
    private Button[] buttons = new Button[buttonTexts.length];

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
        for (int i = 0; i < buttonTexts.length; i++) {
            GlyphLayout layout = new GlyphLayout();
            layout.setText(font, buttonTexts[i]);
            buttons[i] = new Button((int) Gdx.graphics.getWidth() / 2 - (int) layout.width / 2, (int) Gdx.graphics.getHeight() - 200 - i * 75, (int) layout.width, (int) layout.height, buttonTexts[i]);
        }
    }


    @Override
    public void update() {
        for (Button button : buttons) {
            button.update();
            if (button.isClicked()) {
                button.setClicked(false);
                switch (button.getText()) {
                    case "Back to Menu":
                        GameStateManager.getInstance().setCurrentGameState(MenuGameState.getInstance());
                        GameStateManager.getInstance().getCurrentGameState().init();
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
        //draw buttons
        for (Button menuButton : buttons) {
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

package com.trongthien.zbattle.common;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.trongthien.zbattle.view.map.GameMap;
import com.trongthien.zbattle.model.Player;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
public class SharedContext {
    //singleton
    public static SharedContext sharedContext;
    JPanel currentPanel;
    GameMap currentGameMap;
    Player currentPlayer;
    OrthographicCamera camera;

    private SharedContext() {
    }

    public static SharedContext getInstance() {
        if (sharedContext == null) {
            sharedContext = new SharedContext();
        }
        return sharedContext;
    }
}

package com.trongthien.zBattle.component;

import com.trongthien.zBattle.GameMap.GameMap;
import com.trongthien.zBattle.character.Player;
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

    private SharedContext() {
    }

    public static SharedContext getInstance() {
        if (sharedContext == null) {
            sharedContext = new SharedContext();
        }
        return sharedContext;
    }
}

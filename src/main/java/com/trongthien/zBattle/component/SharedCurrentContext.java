package com.trongthien.zBattle.component;

import com.trongthien.zBattle.GameMap.GameMap;
import com.trongthien.zBattle.character.Player;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
public class SharedCurrentContext {
    //singleton
    public static SharedCurrentContext sharedCurrentContext;
    JPanel currentPanel;
    GameMap currentGameMap;
    Player currentPlayer;
    private SharedCurrentContext() {
    }
    public static SharedCurrentContext getInstance() {
        if (sharedCurrentContext == null) {
            sharedCurrentContext = new SharedCurrentContext();
        }
        return sharedCurrentContext;
    }
}

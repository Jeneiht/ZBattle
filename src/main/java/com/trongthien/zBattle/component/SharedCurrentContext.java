package com.trongthien.zBattle.component;

import javax.swing.*;

public class SharedCurrentContext {
    //singleton
    public static SharedCurrentContext sharedCurrentContext;
    JPanel currentPanel;
    private SharedCurrentContext() {
    }
    public static SharedCurrentContext getInstance() {
        if (sharedCurrentContext == null) {
            sharedCurrentContext = new SharedCurrentContext();
        }
        return sharedCurrentContext;
    }
    public void setCurrentPanel(JPanel panel) {
        currentPanel = panel;
    }
}

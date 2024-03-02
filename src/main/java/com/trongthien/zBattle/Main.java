package com.trongthien.zBattle;

import com.trongthien.zBattle.component.SharedContext;
import com.trongthien.zBattle.screen.MainPanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Z Battle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        MainPanel mainPanel = new MainPanel();
        frame.add(mainPanel);
        SharedContext.getInstance().setCurrentPanel(mainPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        mainPanel.startGameThread();
    }
}
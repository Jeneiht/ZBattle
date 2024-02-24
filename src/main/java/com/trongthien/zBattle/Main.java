package com.trongthien.zBattle;

import com.trongthien.zBattle.component.SharedCurrentContext;
import com.trongthien.zBattle.screen.MainPanel;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Z Battle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(false);

        MainPanel mainPanel = new MainPanel();
        frame.add(mainPanel);
        SharedCurrentContext.getInstance().setCurrentPanel(mainPanel);

        frame.pack();

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        mainPanel.startGameThread();
    }
}
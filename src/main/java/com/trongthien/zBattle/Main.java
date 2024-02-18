package com.trongthien.zBattle;

import com.trongthien.zBattle.screen.MainPanel;

import javax.swing.*;
import java.awt.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Hello, World!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        MainPanel mainPanel = new MainPanel();
        frame.add(mainPanel);
        mainPanel.startGameThread();

        frame.pack();


    }
}
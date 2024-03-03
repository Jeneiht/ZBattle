package com.trongthien.zbattle;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.trongthien.zbattle.common.constant.GameConstant;

import java.awt.*;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(0);
		config.setIdleFPS(0);
		config.setTransparentFramebuffer(true);
		
		config.setTitle("ZBattle2");
		config.useVsync(true);
		config.setWindowedMode(GameConstant.screenWidth, GameConstant.screenHeight);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int)(screenSize.getWidth() - GameConstant.screenWidth) / 2;
		int centerY = (int)(screenSize.getHeight() - GameConstant.screenHeight) / 2;

		config.setWindowPosition(centerX, centerY);
		config.setWindowSizeLimits(GameConstant.screenWidth, GameConstant.screenHeight, GameConstant.screenWidth, GameConstant.screenHeight);
		new Lwjgl3Application(new MainGame(), config);
	}
}

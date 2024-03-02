package com.trongthien.zbattle;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.trongthien.zbattle.common.constant.GameConstant;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("ZBattle2");
		config.useVsync(true);
		config.setWindowedMode(GameConstant.screenWidth, GameConstant.screenHeight);
		config.setWindowPosition(0, 0);
		config.setWindowSizeLimits(GameConstant.screenWidth, GameConstant.screenHeight, GameConstant.screenWidth, GameConstant.screenHeight);
		new Lwjgl3Application(new MainGame(), config);
	}
}

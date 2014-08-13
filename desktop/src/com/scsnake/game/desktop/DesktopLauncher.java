package com.scsnake.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.scsnake.game.ScSnake;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Cross Itself Snake";
		cfg.width= 800;
		cfg.height=480;
		new LwjglApplication(new ScSnake(), cfg);
	}
}

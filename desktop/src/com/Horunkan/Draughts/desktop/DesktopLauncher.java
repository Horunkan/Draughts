package com.Horunkan.Draughts.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.Horunkan.Draughts.Draughts;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = Draughts.GAME_TITLE;
		config.height = Draughts.HEIGHT;
		config.width = Draughts.WIDTH;
		config.resizable = false;
		
		new LwjglApplication(new Draughts(), config);
	}
}

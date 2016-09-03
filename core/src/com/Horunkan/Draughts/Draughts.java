package com.Horunkan.Draughts;

import com.badlogic.gdx.Game;

public class Draughts extends Game {
	public static String GAME_TITLE = "Draughts";
	public static int WIDTH = 800;
	public static int HEIGHT = 600;
	
	private MainMenu menu;
	
	@Override
	public void create() {
		menu = new MainMenu();
		this.setScreen(menu);
	}
}

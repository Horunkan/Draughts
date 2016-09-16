package com.Horunkan.Draughts;

import com.Horunkan.Draughts.Views.AbstractScreen;
import com.Horunkan.Draughts.Views.GameScreen;
import com.Horunkan.Draughts.Views.MainMenu;
import com.badlogic.gdx.Game;

public class Draughts extends Game {
	public static String GAME_TITLE = "Draughts";
	public static int WIDTH = 800;
	public static int HEIGHT = 600;
	public static boolean debug = true;
	
	public enum ScreenMode {MAIN_MENU, GAME}
	
	private AbstractScreen menu, game;
	
	@Override
	public void create() {
		menu = new MainMenu(this);
		game = new GameScreen(this);
		this.setScreen(menu);
	}
	
	public void setScreen(ScreenMode mode) {
		if(mode == ScreenMode.MAIN_MENU) this.setScreen(menu);
		else if(mode == ScreenMode.GAME) this.setScreen(game);
	}
}

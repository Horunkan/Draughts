package com.Horunkan.Draughts;

import com.Horunkan.Draughts.Views.*;
import com.badlogic.gdx.Game;

public class Draughts extends Game {
	public enum ScreenMode {MAIN_MENU, NEW_GAME, GAME}
	public static String GAME_TITLE = "Draughts";
	public static int WIDTH = 800;
	public static int HEIGHT = 600;
	
	private MainMenu menu;
	private GameScreen game;
	private NewGame newGame;
	
	@Override
	public void create() {
		menu = new MainMenu(this);
		game = new GameScreen(this);
		newGame = new NewGame(this);
		this.setScreen(menu);
	}
	
	public void setScreen(ScreenMode mode) {
		if(mode == ScreenMode.MAIN_MENU) this.setScreen(menu);
		else if(mode == ScreenMode.GAME) {
			this.setScreen(game);
			game.newGame(newGame.getPlayerNames(), newGame.getBoardName());
		}
		else if(mode == ScreenMode.NEW_GAME) this.setScreen(newGame);
	}
}

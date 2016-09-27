package com.Horunkan.Draughts.Game.Logic;

import com.Horunkan.Draughts.Views.GameScreen;

public class Player {
	public enum Players {BRIGHT, DARK}
	
	private static GameScreen screen;
	private static Players active;
	
	public static void setGameScreen(GameScreen scr) { screen = scr; }
	public static Players getActive() { return active; }
	public static void set(Players player) { active = player; }
	
	public static void change() {
		if(active == Players.BRIGHT) active = Players.DARK;
		else active = Players.BRIGHT;
		if(screen != null) screen.updateActivePlayer();
	}
}

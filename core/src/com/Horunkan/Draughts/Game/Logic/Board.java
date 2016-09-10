package com.Horunkan.Draughts.Game.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Board {
	private int board[][];
	
	public Board() {
		loadFromFile();
	}
	
	private void loadFromFile() {
		FileHandle file = Gdx.files.internal("Boards/8x8.txt");
		String lines[] = file.readString().split(" #END");
		for(int i = 0; i < lines.length; ++i) lines[i] = lines[i].replaceAll("\\s", ""); //Remove white chars
		
		int boardY = lines.length;
		int boardX = lines[0].length();
		
		board = new int[boardX][boardY];
				
		for(int y = 0; y < boardY; ++y) {
			for(int x = 0; x < boardX; ++x) {
				board[x][y] = Character.getNumericValue(lines[y].charAt(x));
			}
		}
	}
}

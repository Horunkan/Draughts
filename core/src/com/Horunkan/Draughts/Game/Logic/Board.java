package com.Horunkan.Draughts.Game.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Board {
	private int board[][];
	private int boardWidth, boardHeight;
	
	public Board() {
		loadFromFile();
	}
	
	private void loadFromFile() {
		FileHandle file = Gdx.files.internal("Boards/8x8.txt");
		String lines[] = file.readString().split(" #END");
		for(int i = 0; i < lines.length; ++i) lines[i] = lines[i].replaceAll("\\s", ""); //Remove white chars
		
		boardHeight = lines.length;
		boardWidth = lines[0].length();
		
		board = new int[boardWidth][boardHeight];
				
		for(int y = 0; y < boardHeight; ++y) {
			for(int x = 0; x < boardWidth; ++x) {
				board[x][y] = Character.getNumericValue(lines[y].charAt(x));
			}
		}
	}
	
	public int getWidth() { return boardWidth; }
	public int getHeight() { return boardHeight; }
	public int getValue(int x, int y) { return board[x][y]; }
	
	public int countPawns(int val) {
		int counter = 0;
		
		for(int y = 0; y < boardHeight; ++y){
			for(int x = 0; x < boardWidth; ++x){
				if(board[x][y] == val) ++counter;
			}
		}
		
		return counter;
	}
}

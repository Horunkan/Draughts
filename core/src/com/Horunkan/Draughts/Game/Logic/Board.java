package com.Horunkan.Draughts.Game.Logic;

import com.Horunkan.Draughts.BoardPosition;
import com.Horunkan.Draughts.Game.GUI.DrawCell;
import com.Horunkan.Draughts.Game.GUI.DrawPawn;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Board extends BoardDebug {
	private int board[][];
	private int boardWidth, boardHeight;
	private DrawPawn activePawn = null;
	
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
	
	public void setActivePawn(DrawPawn pawn) {
		activePawn = pawn;
		if(!debug) activePawn.setColor(Color.CYAN);
		else setColorDebug(true);
	}
	
	public boolean canMove(DrawCell cell) {
		BoardPosition cellPos = cell.getBoardPosition();
		
		if(activePawn == null) return false;
		else if(board[cellPos.x][cellPos.y] == 0) return false;
		else {
			BoardPosition distance = BoardPosition.getDistance(cellPos, activePawn.getBoardPosition());
			
			if(distance.x == 1 && distance.y == 1) return true; //Across movement
		}
		
		return false;
	}
	
	public void movePawn(Vector2 pos, int newPosX, int newPosY) {
		board[activePawn.getBoardPosition().x][activePawn.getBoardPosition().y] = 1;
		board[newPosX][newPosY] = activePawn.getPawnType();
		activePawn.setPosition(pos.x, pos.y);
		activePawn.setBoardPosition(newPosX, newPosY);
	}
	
	public void unselectPawn() {
		if(!debug) activePawn.setColor(Color.WHITE);
		else setColorDebug(false);
		activePawn = null;
	}
	
	public DrawPawn getPawn() { return activePawn; }
	
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

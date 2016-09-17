package com.Horunkan.Draughts.Game.Logic;

import com.Horunkan.Draughts.BoardPosition;
import com.Horunkan.Draughts.Draughts;
import com.Horunkan.Draughts.Game.GUI.DrawCell;
import com.Horunkan.Draughts.Game.GUI.DrawPawn;
import com.Horunkan.Draughts.Views.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Board extends BoardDebug {
	private int board[][];
	private int boardWidth, boardHeight;
	private DrawPawn activePawn = null;
	private GameScreen screen;
	
	public Board(GameScreen screen) {
		this.screen = screen;
		loadFromFile();
	}
	
	private void loadFromFile() {
		FileHandle file = Gdx.files.internal("Boards/CapturesDebug.txt"); //8x8.txt
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
		updatePawnColor();
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
	
	
	public enum CaptureDirection {NO_CAPTURE, TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT}
	
	
	public enum PawnType {PLAYER_A, PLAYER_B}
	
	public CaptureDirection getCaptureDirection() {
		if(activePawn == null) return CaptureDirection.NO_CAPTURE;
		
		BoardPosition pawnPos = activePawn.getBoardPosition();
		
		if(canCaptureTopLeft(pawnPos)) return CaptureDirection.TOP_LEFT;
		if(canCaptureTopRight(pawnPos)) return CaptureDirection.TOP_RIGHT;
		if(canCaptureBottomLeft(pawnPos)) return CaptureDirection.BOTTOM_LEFT;
		if(canCaptureBottomRight(pawnPos)) return CaptureDirection.BOTTOM_RIGHT;
		
		return CaptureDirection.NO_CAPTURE;
	}
	
	public void capture(CaptureDirection dir) {
		if(dir == CaptureDirection.TOP_LEFT) removePawn(1, 1);
		else if(dir == CaptureDirection.TOP_RIGHT) removePawn(-1, 1);
		else if(dir == CaptureDirection.BOTTOM_LEFT) removePawn(1, -1);
		else if(dir == CaptureDirection.BOTTOM_RIGHT) removePawn(-1, -1);
	}
	
	public void removePawn(int xChange, int yChange) {
		BoardPosition pos = activePawn.getBoardPosition();
		board[pos.x + xChange][pos.y + yChange] = 1;
		screen.removePawn(pos.x + xChange, pos.y + yChange);
	}
	
	//TODO Separate canCapture.... to different class
	public boolean canCaptureTopLeft(BoardPosition pos) {
		if(pos.x > 0 && pos.y > 0) {
			int pawnValue = activePawn.getPawnTypeInt();
			int cellValue = board[pos.x - 1][pos.y - 1];
			
			if(cellValue == 0 || cellValue == 1) return false;
			
			if(cellValue != pawnValue) {
				if(pos.x - 2 >= 0 && pos.y - 2 >= 0) {
					if(board[pos.x - 2][pos.y - 2] == 1) return true;
				}
			}
		}
		return false;
	}
	
	public boolean canCaptureTopRight(BoardPosition pos) {
		if(pos.x + 1 < getWidth() && pos.y > 0) {
			int pawnValue = activePawn.getPawnTypeInt();
			int cellValue = board[pos.x + 1][pos.y - 1];
			
			if(cellValue == 0 || cellValue == 1) return false;
			
			if(cellValue != pawnValue) {
				if(pos.x + 3 <= getWidth() && pos.y - 2 >= 0) {
					if(board[pos.x + 2][pos.y - 2] == 1) return true;
				}
			}
		}
		return false;
	}
	
	
	
	
	
	public boolean canCaptureBottomLeft(BoardPosition pos) {
		if(pos.x > 0 && pos.y + 1 < getHeight()) {
			int pawnValue = activePawn.getPawnTypeInt();
			int cellValue = board[pos.x - 1][pos.y + 1];
			
			if(cellValue == 0 || cellValue == 1) return false;
			
			if(cellValue != pawnValue) {
				if(pos.x - 2 >= 0 && pos.y + 3 <= getHeight()) {
					if(board[pos.x - 2][pos.y + 2] == 1) return true;
				}
			}
		}
		return false;
	}
	
	public boolean canCaptureBottomRight(BoardPosition pos) {
		if(pos.x + 1 < getWidth() && pos.y + 1 < getHeight()) {
			int pawnValue = activePawn.getPawnTypeInt();
			int cellValue = board[pos.x + 1][pos.y + 1];
			
			if(cellValue == 0 || cellValue == 1) return false;
			
			if(cellValue != pawnValue) {
				if(pos.x + 3 <= getWidth() && pos.y + 3 <= getHeight()) {
					if(board[pos.x + 2][pos.y + 2] == 1) return true;
				}
			}
		}
		return false;
	}
	
	public void movePawn(Vector2 pos, int newPosX, int newPosY) {
		board[activePawn.getBoardPosition().x][activePawn.getBoardPosition().y] = 1;
		board[newPosX][newPosY] = activePawn.getPawnTypeInt();
		activePawn.setPosition(pos.x, pos.y);
		activePawn.setBoardPosition(newPosX, newPosY);
	}
	
	public void unselectPawn() {
		activePawn = null;
		updatePawnColor();
	}
	
	public void updatePawnColor() {
		if(!Draughts.debug) {
			if(activePawn == null) activePawn.setColor(Color.WHITE);
			else activePawn.setColor(Color.CYAN);
		}
		else {
			 setColorDebug(false);
			 if(activePawn != null) setColorDebug(true);
		}
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

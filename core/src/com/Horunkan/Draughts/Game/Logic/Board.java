package com.Horunkan.Draughts.Game.Logic;

import com.Horunkan.Draughts.Game.GUI.*;
import com.Horunkan.Draughts.Game.GUI.DrawPawn.PawnType;
import com.Horunkan.Draughts.Game.Logic.Player.Players;
import com.Horunkan.Draughts.Utilities.*;
import com.Horunkan.Draughts.Views.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Board {
	public enum CaptureDirection {NO_CAPTURE, TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT}
	
	private int board[][];
	private int boardWidth, boardHeight;
	private DrawPawn activePawn = null;
	private GameScreen screen;
	
	public Board(GameScreen screen) { this.screen = screen; }
	
	public void loadFromFile(String boardName) {
		FileHandle file = Gdx.files.internal("Boards/" + boardName);
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

	public void selectPawn(DrawPawn pawn) {
		ActivePawn.select(pawn);
		activePawn = ActivePawn.get();
	}
	
	public void unselectPawn() {
		ActivePawn.unselect();	
		activePawn = null;
	}
		
	public boolean canCapture(DrawCell cell) {
		BoardPosition cellPos = cell.getBoardPosition();
		
		if(activePawn == null) return false;
		else {
			BoardPosition distance = BoardPosition.getDistance(cellPos, activePawn.getBoardPosition());
			if(distance.x == 2 && distance.y == 2 && getCaptureDirection(activePawn.getBoardPosition()) != CaptureDirection.NO_CAPTURE) return true;
			else if(activePawn.getType() == PawnType.KING && distance.x == distance.y && getCaptureDirection(cellPos) != CaptureDirection.NO_CAPTURE) return true;
		}
		
		return false;
	}
		
	public boolean canCapture(BoardPosition cellWithPawn, BoardPosition cellToMove) {
		int cellValue = getValue(cellWithPawn.x, cellWithPawn.y);
		
		if(cellValue == 0 || cellValue == 1) return false;
		else {
			Players pawnActive = activePawn.getPlayer();
			Players pawnCapture = getPawnPlayer(cellWithPawn.x, cellWithPawn.y);
			
			if(pawnActive != pawnCapture) {
				int newPosition = getValue(cellToMove.x, cellToMove.y);
				if(newPosition == 1 || newPosition == activePawn.getPawnPlayerInt()) return true;
				else return false;
			}
		}		
		return false;
	}
	
	public CaptureDirection getCaptureDirection(BoardPosition pos) {
		if(activePawn == null) return CaptureDirection.NO_CAPTURE;
		
		if(canCapture(new BoardPosition(pos.x - 1, pos.y - 1), new BoardPosition(pos.x - 2, pos.y - 2))) return CaptureDirection.TOP_LEFT;
		if(canCapture(new BoardPosition(pos.x + 1, pos.y - 1), new BoardPosition(pos.x + 2, pos.y - 2))) return CaptureDirection.TOP_RIGHT;
		if(canCapture(new BoardPosition(pos.x - 1, pos.y + 1), new BoardPosition(pos.x - 2, pos.y + 2))) return CaptureDirection.BOTTOM_LEFT;
		if(canCapture(new BoardPosition(pos.x + 1, pos.y + 1), new BoardPosition(pos.x + 2, pos.y + 2))) return CaptureDirection.BOTTOM_RIGHT;
				
		return CaptureDirection.NO_CAPTURE;
	}
	
	public void capture(CaptureDirection dir, BoardPosition pos) {
		if(dir == CaptureDirection.TOP_LEFT) removePawn(pos, -1, -1);
		else if(dir == CaptureDirection.TOP_RIGHT) removePawn(pos, 1, -1);
		else if(dir == CaptureDirection.BOTTOM_LEFT) removePawn(pos, -1, 1);
		else if(dir == CaptureDirection.BOTTOM_RIGHT) removePawn(pos, 1, 1);
		screen.countPawns();
	}
	
	public void removePawn(BoardPosition pos, int xChange, int yChange) {
		board[pos.x + xChange][pos.y + yChange] = 1;
		screen.removePawn(pos.x + xChange, pos.y + yChange);
	}
	
	public int getWidth() { return boardWidth; }
	public int getHeight() { return boardHeight; }
	
	public void setValue(BoardPosition pos, int val) {
		if(getValue(pos.x, pos.y) != -1) board[pos.x][pos.y] = val;
	}
	
	public void setValue(int x, int y, int val) {
		if(getValue(x, y) != -1) board[x][y] = val;
	}
	
	public int getValue(BoardPosition pos) {
		return getValue(pos.x, pos.y);
	}
	
	public int getValue(int x, int y) { 
		if(x >= 0 && x < getWidth() && y >= 0 && y < getHeight()) return board[x][y];
		else return -1;
	}
	
	private Players getPawnPlayer(int x, int y) {
		if(getValue(x,y) == 2 || getValue(x,y) == 4) return Players.BRIGHT;
		else return Players.DARK;
	}
	
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

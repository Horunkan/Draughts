package com.Horunkan.Draughts.Game.Logic;

import com.Horunkan.Draughts.Draughts;
import com.Horunkan.Draughts.Game.GUI.DrawCell;
import com.Horunkan.Draughts.Game.GUI.DrawPawn;
import com.Horunkan.Draughts.Game.GUI.DrawPawn.PawnType;
import com.Horunkan.Draughts.Utilities.BoardPosition;
import com.Horunkan.Draughts.Views.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Board extends BoardDebug {
	public enum CaptureDirection {NO_CAPTURE, TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT}
	public enum Player {BRIGHT, DARK}
	
	private int board[][];
	private int boardWidth, boardHeight;
	private DrawPawn activePawn = null;
	private GameScreen screen;
	private Player activePlayer;
	private final float pawnMovementSpeed = 0.15f;
	
	public Board(GameScreen screen) {
		this.screen = screen;
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
	
	public Player getActivePlayer() { return activePlayer; }
	public void setPlayer(Player player) { activePlayer = player; }
	
	public void changePlayer() {
		if(activePlayer == Player.BRIGHT) activePlayer = Player.DARK;
		else activePlayer = Player.BRIGHT;
		screen.updateActivePlayer(activePlayer);
	}
	
	public void setActivePawn(DrawPawn pawn) {
		activePawn = pawn;
		
		if(!Draughts.debug) activePawn.setColor(Color.CYAN);
		else setColorDebug(true);
	}
	
	public void unselectPawn() {
		if(!Draughts.debug) activePawn.setColor(Color.WHITE);
		else setColorDebug(false);
		
		activePawn = null;
	}
	
	public boolean canMove(DrawCell cell) {
		BoardPosition cellPos = cell.getBoardPosition();
		
		if(activePawn == null) return false;
		else if(board[cellPos.x][cellPos.y] == 0) return false;
		else {
			BoardPosition distance = BoardPosition.getDistance(cellPos, activePawn.getBoardPosition());
			if(activePawn.getPawnType() == PawnType.STANDARD && distance.x == 1 && distance.y == 1) return true;
			else if(activePawn.getPawnType() == PawnType.KING && distance.x == distance.y) {
				BoardPosition direction = new BoardPosition(cellPos.x - activePawn.getBoardPosition().x, cellPos.y - activePawn.getBoardPosition().y);
				return canMoveKing(activePawn.getBoardPosition(), cellPos, direction);
			}
		}
		
		return false;
	}
	
	private boolean canMoveKing(BoardPosition checkPos, BoardPosition destination, BoardPosition direction) {
		if(destination.isEqual(checkPos.x, checkPos.y)) { return true; }
		else if(getValue(checkPos.x, checkPos.y) != 1 && !activePawn.getBoardPosition().isEqual(checkPos.x, checkPos.y)) return false;
		
		if(direction.x < 0 && direction.y < 0) {
			return canMoveKing(new BoardPosition(checkPos.x - 1, checkPos.y - 1), destination, direction); //Top left
		}
		if(direction.x > 0 && direction.y < 0) {
			return canMoveKing(new BoardPosition(checkPos.x + 1, checkPos.y - 1), destination, direction); //Top right
		}
		if(direction.x < 0 && direction.y > 0) {
			return canMoveKing(new BoardPosition(checkPos.x - 1, checkPos.y + 1), destination, direction); //Bottom left
		}
		if(direction.x > 0 && direction.y > 0) {
			return canMoveKing(new BoardPosition(checkPos.x + 1, checkPos.y + 1), destination, direction); //Bottom right
		}

		return false;
	}
	
	public boolean canCapture(DrawCell cell) {
		BoardPosition cellPos = cell.getBoardPosition();
		
		if(activePawn == null) return false;
		else if(board[cellPos.x][cellPos.y] == 0) return false;
		else {
			BoardPosition distance = BoardPosition.getDistance(cellPos, activePawn.getBoardPosition());
			if(distance.x == 2 && distance.y == 2 && getCaptureDirection(activePawn.getBoardPosition()) != CaptureDirection.NO_CAPTURE) return true;
			else if(activePawn.getPawnType() == PawnType.KING && distance.x == distance.y && getCaptureDirection(cellPos) != CaptureDirection.NO_CAPTURE) return true;
		}
		
		return false;
	}
	
	public void movePawn(Vector2 pos, int newPosX, int newPosY) {
		board[activePawn.getBoardPosition().x][activePawn.getBoardPosition().y] = 1;
		board[newPosX][newPosY] = activePawn.getPawnPlayerInt();
		activePawn.setBoardPosition(newPosX, newPosY);
		activePawn.addAction(Actions.moveTo(pos.x, pos.y, pawnMovementSpeed));
		if(canChangeToKing(newPosY)) activePawn.setAsKing();
		screen.countPawns();
	}
	
	private boolean canChangeToKing(int posY) {
		if(activePawn.getPawnPlayer() == Player.BRIGHT && posY == boardHeight - 1) return true;
		else if(activePawn.getPawnPlayer() == Player.DARK && posY == 0) return true;
		return false;
	}
	
	public boolean canCapture(BoardPosition cellWithPawn, BoardPosition cellToMove) {
		int cellValue = getValue(cellWithPawn.x, cellWithPawn.y);
		
		if(cellValue == 0 || cellValue == 1) return false;
		else {
			Player pawnActive = activePawn.getPawnPlayer();
			Player pawnCapture = getPawnPlayer(cellWithPawn.x, cellWithPawn.y);
			
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
	
	public DrawPawn getPawn() { return activePawn; }
	public int getWidth() { return boardWidth; }
	public int getHeight() { return boardHeight; }
	
	public int getValue(int x, int y) { 
		if(x >= 0 && x < getWidth() && y >= 0 && y < getHeight()) return board[x][y];
		else return -1;
	}
	
	private Player getPawnPlayer(int x, int y) {
		if(getValue(x,y) == 2 || getValue(x,y) == 4) return Player.BRIGHT;
		else return Player.DARK;
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

package com.Horunkan.Draughts.Game.Logic;

import com.Horunkan.Draughts.Game.GUI.DrawCell;
import com.Horunkan.Draughts.Game.GUI.DrawPawn;
import com.Horunkan.Draughts.Game.GUI.DrawPawn.PawnType;
import com.Horunkan.Draughts.Game.Logic.Player.Players;
import com.Horunkan.Draughts.Utilities.BoardPosition;
import com.Horunkan.Draughts.Views.GameScreen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class ActivePawn {
	public enum CaptureDirection {NO_CAPTURE, TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT}
	
	private static final float pawnMovementSpeed = 0.15f;
	
	private static GameScreen screen;
	private static Board board;
	private static DrawPawn selected;
	private static DrawCell selectedCell;
	
	public static void setGameScreen(GameScreen scr) { screen = scr; }
	public static void setBoard(Board scr) { board = scr; }
	
	public static void select(DrawPawn pawn) {
		selected = pawn;
		selectedCell = screen.getCell(selected.getBoardPosition());
		selectedCell.setColor(Color.GREEN);
	}
	
	public static void unselect() {
		if(selectedCell != null) selectedCell.setColor(Color.WHITE);
		selected = null;
		selectedCell = null;
	}
	
	public static boolean canMove(DrawCell cell) {
		BoardPosition distance = BoardPosition.getDistance(cell.getBoardPosition(), selected.getBoardPosition());
		
		if(selected.getType() == PawnType.STANDARD) return (distance.x == 1) && (distance.y == 1);
		else {
			if(distance.x == distance.y) return canMoveKing(selected.getBoardPosition(), cell);
		}
		return false;
	}
	
	private static boolean canMoveKing(BoardPosition checkPos, DrawCell destination) {
		if(destination.getBoardPosition().isEqual(checkPos)) return true;
		else if(board.getValue(checkPos) != 1 && !selected.getBoardPosition().isEqual(checkPos)) return false;
		
		BoardPosition dir = BoardPosition.getDirection(destination.getBoardPosition(), selected.getBoardPosition());
		BoardPosition newCheckPos = new BoardPosition();
		
		if(dir.x < 0 && dir.y < 0) newCheckPos =  new BoardPosition(checkPos.x - 1, checkPos.y - 1); //Top left
		else if(dir.x > 0 && dir.y < 0) newCheckPos =  new BoardPosition(checkPos.x + 1, checkPos.y - 1); //Top right
		else if(dir.x < 0 && dir.y > 0) newCheckPos =  new BoardPosition(checkPos.x - 1, checkPos.y + 1); //Bottom left
		else if(dir.x > 0 && dir.y > 0) newCheckPos =  new BoardPosition(checkPos.x + 1, checkPos.y + 1); //Bottom right
		
		return canMoveKing(newCheckPos, destination);
	}
	
	public static void move(Vector2 ScreenPos, BoardPosition boardPos) {
		board.setValue(selected.getBoardPosition(), 1);
		board.setValue(boardPos, selected.getPlayerInt());
		selected.setBoardPosition(boardPos);
		selected.addAction(Actions.moveTo(ScreenPos.x, ScreenPos.y, pawnMovementSpeed));
		if(canChangeToKing(boardPos.y)) selected.setAsKing();
		screen.countPawns();
	}
	
	private static boolean canChangeToKing(int posY) {
		if(selected.getPlayer() == Players.BRIGHT && posY == board.getHeight() - 1) return true;
		else if(selected.getPlayer() == Players.DARK && posY == 0) return true;
		else return false;
	}
	
	public static boolean canCapturePawn(BoardPosition cellPos) {
		BoardPosition distance = BoardPosition.getDistance(cellPos, selected.getBoardPosition());
		
		if(selected.getType() == PawnType.STANDARD) {
			if(distance.x == 2 && distance.y == 2 && getCaptureDirection(selected.getBoardPosition()) != CaptureDirection.NO_CAPTURE) return true;
		}
		else {
			if(distance.x == distance.y && getCaptureDirection(cellPos) != CaptureDirection.NO_CAPTURE) return true;
		}
		
		return false;
	}
	
	public static CaptureDirection getCaptureDirection(BoardPosition pos) {
		if(canCapture(new BoardPosition(pos.x - 1, pos.y - 1), new BoardPosition(pos.x - 2, pos.y - 2))) return CaptureDirection.TOP_LEFT;
		if(canCapture(new BoardPosition(pos.x + 1, pos.y - 1), new BoardPosition(pos.x + 2, pos.y - 2))) return CaptureDirection.TOP_RIGHT;
		if(canCapture(new BoardPosition(pos.x - 1, pos.y + 1), new BoardPosition(pos.x - 2, pos.y + 2))) return CaptureDirection.BOTTOM_LEFT;
		if(canCapture(new BoardPosition(pos.x + 1, pos.y + 1), new BoardPosition(pos.x + 2, pos.y + 2))) return CaptureDirection.BOTTOM_RIGHT;
		
		return CaptureDirection.NO_CAPTURE;
	}
	
	public static boolean canCapture(BoardPosition pawnToCapture, BoardPosition cellToMove) {
		if(board.getValue(pawnToCapture) == 0 || board.getValue(pawnToCapture) == 1) return false;
		
		Players activePlayer = selected.getPlayer();
		Players pawnToCapturePlayer = board.getPawnPlayer(pawnToCapture);
		
		if(activePlayer == pawnToCapturePlayer) return false;
		else {
			if(board.getValue(cellToMove) == 1 || board.getValue(cellToMove) == selected.getPlayerInt()) return true;
			else return false;
		}
	}
	
	public static DrawPawn get() { return selected; }
	
	public static boolean isSelected() {
		if(selected == null) return false;
		else return true;
	}
}

package com.Horunkan.Draughts.Game.Logic;

import com.Horunkan.Draughts.BoardPosition;
import com.Horunkan.Draughts.FontLoader;
import com.Horunkan.Draughts.Game.GUI.DrawCell;
import com.Horunkan.Draughts.Game.GUI.DrawPawn;
import com.Horunkan.Draughts.Views.GameScreen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BoardDebug {
	protected DrawCell[][] boardCells;
	protected DrawPawn[] pawnsBright, pawnsDark;
	protected GameScreen screen;
	
	private Board board;
	private SpriteBatch batch;
	private BitmapFont font;
	private int checkCaptures = 0;
	private int maxCapturesCheck = 10;
	
	public void debug(GameScreen screen, Board board, DrawCell[][] cells, DrawPawn[] pawnsBright, DrawPawn[] pawnsDark) {
		boardCells = cells;
		this.pawnsBright = pawnsBright;
		this.pawnsDark = pawnsDark;
		this.screen = screen;
		this.board = board;
		
		batch = new SpriteBatch();
		FontLoader fontLoader = FontLoader.getInstance();
		font = fontLoader.getFont(15);
	}
	
	public void renderDebug() {
		batch.setProjectionMatrix(screen.camera.combined);
		
		batch.begin();
		drawBoardState();
		batch.end();
	}
	
	protected void setColorDebug(boolean selectedPawn) {
		if(!selectedPawn) {
			for(DrawCell cly[] : boardCells) {
				for(DrawCell clx : cly) clx.setColor(Color.WHITE); //Reset cells color
			}
		}
		else {
			checkCaptures = 0;
			highlightMovement();
			highlightCaptures(board.getPawn().getBoardPosition().x, board.getPawn().getBoardPosition().y);
		}
	}
	
	private void highlightCaptures(int startPosX, int startPosY) {
		if(canCaptureTopLeft(startPosX, startPosY)) {
			boardCells[startPosX - 1][startPosY - 1].setColor(Color.BLUE);
			boardCells[startPosX - 2][startPosY - 2].setColor(Color.GREEN);
			++checkCaptures;
			
			if(checkCaptures < maxCapturesCheck) highlightCaptures(startPosX - 2, startPosY - 2);
		}
		
		if(canCaptureTopRight(startPosX, startPosY)) {
			boardCells[startPosX + 1][startPosY - 1].setColor(Color.BLUE);
			boardCells[startPosX + 2][startPosY - 2].setColor(Color.GREEN);
			++checkCaptures;
			
			if(checkCaptures < maxCapturesCheck) highlightCaptures(startPosX + 2, startPosY - 2);
		}
		
		if(canCaptureBottomLeft(startPosX, startPosY)) {
			boardCells[startPosX - 1][startPosY + 1].setColor(Color.BLUE);
			boardCells[startPosX - 2][startPosY + 2].setColor(Color.GREEN);
			++checkCaptures;
			
			if(checkCaptures < maxCapturesCheck)highlightCaptures(startPosX - 2, startPosY + 2);
		}
		
		if(canCaptureBottomRight(startPosX, startPosY)) {
			boardCells[startPosX + 1][startPosY + 1].setColor(Color.BLUE);
			boardCells[startPosX + 2][startPosY + 2].setColor(Color.GREEN);
			++checkCaptures;
			
			if(checkCaptures < maxCapturesCheck)highlightCaptures(startPosX + 2, startPosY + 2);
		}
	}
	
	private boolean canCaptureTopLeft(int x, int y) {
		if(x > 0 && y > 0) {
			int pawnValue = board.getPawn().getPawnType();
			int cellValue = board.getValue(x - 1, y - 1);
			
			if((cellValue == 2 && pawnValue == 3) || (cellValue == 3 && pawnValue == 2)) {
				if(x - 2 >= 0 && y - 2 >= 0) {
					if(board.getValue(x - 2, y - 2) == 1) return true;
				}
			}
		}
		return false;
	}
	
	private boolean canCaptureTopRight(int x, int y) {
		if(x + 1 < board.getWidth() && y > 0) {
			int pawnValue = board.getPawn().getPawnType();
			int cellValue = board.getValue(x + 1, y - 1);
			
			if((cellValue == 2 && pawnValue == 3) || (cellValue == 3 && pawnValue == 2)) {
				if(x + 3 <= board.getWidth() && y - 2 >= 0) {
					if(board.getValue(x + 2, y - 2) == 1) return true;
				}
			}
		}
		return false;
	}
	
	private boolean canCaptureBottomLeft(int x, int y) {
		if(x > 0 && y + 1 < board.getHeight()) {
			int pawnValue = board.getPawn().getPawnType();
			int cellValue = board.getValue(x - 1, y + 1);
			
			if((cellValue == 2 && pawnValue == 3) || (cellValue == 3 && pawnValue == 2)) {
				if(x - 2 >= 0 && y + 3 <= board.getHeight()) {
					if(board.getValue(x - 2, y + 2) == 1) return true;
				}
			}
		}
		return false;
	}
	
	private boolean canCaptureBottomRight(int x, int y) {
		if(x + 1 < board.getWidth() && y + 1 < board.getHeight()) {
			int pawnValue = board.getPawn().getPawnType();
			int cellValue = board.getValue(x + 1, y + 1);
			
			if((cellValue == 2 && pawnValue == 3) || (cellValue == 3 && pawnValue == 2)) {
				if(x + 3 <= board.getWidth() && y + 3 <= board.getHeight()) {
					if(board.getValue(x + 2, y + 2) == 1) return true;
				}
			}
		}
		return false;
	}
	
	private void highlightMovement() {
		BoardPosition pos = board.getPawn().getBoardPosition();
		boardCells[pos.x][pos.y].setColor(Color.CYAN); //Highlight cell where pawn is selected
		
		if(pos.x > 0 && pos.y > 0) setCellCornerColor(boardCells[pos.x - 1][pos.y - 1], board.getValue(pos.x - 1, pos.y - 1)); //Top left
		if(pos.x + 1 < board.getWidth() && pos.y > 0) setCellCornerColor(boardCells[pos.x + 1][pos.y - 1], board.getValue(pos.x + 1, pos.y - 1)); //Top right
		if(pos.x > 0 && pos.y + 1 < board.getHeight()) setCellCornerColor(boardCells[pos.x - 1][pos.y + 1], board.getValue(pos.x - 1, pos.y + 1)); //Bottom left
		if(pos.x + 1 < board.getWidth() && pos.y + 1 < board.getHeight()) setCellCornerColor(boardCells[pos.x + 1][pos.y + 1], board.getValue(pos.x + 1, pos.y + 1)); //Bottom right
	}
	
	private void setCellCornerColor(DrawCell cell, int cellValue) {
		int pawnType = board.getPawn().getPawnType();
		
		if(cellValue == 1) cell.setColor(Color.GREEN);
		else if(cellValue == 2 && pawnType == 2) cell.setColor(Color.RED);
		else if(cellValue == 2 && pawnType == 3) cell.setColor(Color.RED);
		else if(cellValue == 3 && pawnType == 3) cell.setColor(Color.RED);
		else if(cellValue == 3 && pawnType == 2) cell.setColor(Color.RED);
	}
		
	private void drawBoardState() {
		for(int y = 0; y < board.getHeight(); ++y) {
			for(int x = 0; x < board.getWidth(); ++x) {
				font.draw(batch, Integer.toString(board.getValue(x, y)), boardCells[x][y].getPosition().x + 2, boardCells[x][y].getPosition().y + 63);
			}
		}
	}
}

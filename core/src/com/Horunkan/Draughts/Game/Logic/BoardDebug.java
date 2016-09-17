package com.Horunkan.Draughts.Game.Logic;

import com.Horunkan.Draughts.BoardPosition;
import com.Horunkan.Draughts.FontLoader;
import com.Horunkan.Draughts.Game.GUI.DrawCell;
import com.Horunkan.Draughts.Game.GUI.DrawPawn;
import com.Horunkan.Draughts.Game.Logic.Board.PawnType;
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
			highlightCaptures(board.getPawn().getBoardPosition());
		}
	}
	
	private void highlightCaptures(BoardPosition pos/*int startPosX, int startPosY*/) {
		if(board.canCaptureTopLeft(pos)) {
			boardCells[pos.x - 1][pos.y - 1].setColor(Color.BLUE);
			boardCells[pos.x - 2][pos.y - 2].setColor(Color.GREEN);
			++checkCaptures;
			
			if(checkCaptures < maxCapturesCheck) highlightCaptures(new BoardPosition(pos.x - 2, pos.y - 2));
		}
		
		if(board.canCaptureTopRight(pos)) {
			boardCells[pos.x + 1][pos.y - 1].setColor(Color.BLUE);
			boardCells[pos.x + 2][pos.y - 2].setColor(Color.GREEN);
			++checkCaptures;
			
			if(checkCaptures < maxCapturesCheck) highlightCaptures(new BoardPosition(pos.x + 2, pos.y - 2));
		}
		
		if(board.canCaptureBottomLeft(pos)) {
			boardCells[pos.x - 1][pos.y + 1].setColor(Color.BLUE);
			boardCells[pos.x - 2][pos.y + 2].setColor(Color.GREEN);
			++checkCaptures;
			
			if(checkCaptures < maxCapturesCheck) highlightCaptures(new BoardPosition(pos.x - 2, pos.y + 2));
		}
		
		if(board.canCaptureBottomRight(pos)) {
			boardCells[pos.x + 1][pos.y + 1].setColor(Color.BLUE);
			boardCells[pos.x + 2][pos.y + 2].setColor(Color.GREEN);
			++checkCaptures;
			
			if(checkCaptures < maxCapturesCheck) highlightCaptures(new BoardPosition(pos.x + 2, pos.y + 2));
		}
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
		if(cellValue == 1) cell.setColor(Color.GREEN);
		else cell.setColor(Color.RED);
	}
		
	private void drawBoardState() {
		for(int y = 0; y < board.getHeight(); ++y) {
			for(int x = 0; x < board.getWidth(); ++x) {
				font.draw(batch, Integer.toString(board.getValue(x, y)), boardCells[x][y].getPosition().x + 2, boardCells[x][y].getPosition().y + 63);
			}
		}
	}
}

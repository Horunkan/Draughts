package com.Horunkan.Draughts.Game.Logic;

import java.util.ArrayList;

import com.Horunkan.Draughts.Game.GUI.DrawCell;
import com.Horunkan.Draughts.Game.GUI.DrawPawn;
import com.Horunkan.Draughts.Game.GUI.DrawPawn.PawnType;
import com.Horunkan.Draughts.Utilities.BoardPosition;
import com.Horunkan.Draughts.Utilities.FontLoader;
import com.Horunkan.Draughts.Views.GameScreen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class BoardDebug {
	protected DrawCell[][] boardCells;
	protected GameScreen screen;
	protected ArrayList<DrawPawn> pawns;
	
	private Board board;
	private SpriteBatch batch;
	private BitmapFont font;
	private int checkCaptures = 0;
	private int maxCapturesCheck = 10;
	
	public void debug(GameScreen screen, Board board, DrawCell[][] cells, ArrayList<DrawPawn> pawns) {
		boardCells = cells;
		this.pawns = pawns;
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
	
	private void drawBoardState() {
		for(int y = 0; y < board.getHeight(); ++y) {
			for(int x = 0; x < board.getWidth(); ++x) {
				font.draw(batch, Integer.toString(board.getValue(x, y)), boardCells[x][y].getPosition().x + 2, boardCells[x][y].getPosition().y + 63);
			}
		}
	}
	
	protected void setColorDebug(boolean selectedPawn) {
		if(!selectedPawn) {
			for(DrawCell cly[] : boardCells) {
				for(DrawCell clx : cly) clx.setColor(Color.WHITE); //Reset cells color
			}
		}
		else {
			checkCaptures = 0;
			BoardPosition pos = board.getPawn().getBoardPosition();
			boardCells[pos.x][pos.y].setColor(Color.CYAN); //Highlight cell where pawn is selected
			highlightMovement(pos);
			highlightCaptures(pos);
		}
	}
	
	private void highlightMovement(BoardPosition pos) {
		highlightTopLeft(pos);
		highlightTopRight(pos);
		highlightBottomLeft(pos);
		highlightBottomRight(pos);
	}
	
	private void highlightTopLeft(BoardPosition pos) {
		if(pos.x > 0 && pos.y > 0) {
			setCellCornerColor(boardCells[pos.x - 1][pos.y - 1], board.getValue(pos.x - 1, pos.y - 1));
			if(board.getPawn().getPawnType() == PawnType.KING) highlightTopLeft(new BoardPosition(pos.x - 1, pos.y - 1));
		}
	}
	
	private void highlightTopRight(BoardPosition pos) {
		if(pos.x + 1 < board.getWidth() && pos.y > 0) {
			setCellCornerColor(boardCells[pos.x + 1][pos.y - 1], board.getValue(pos.x + 1, pos.y - 1));
			if(board.getPawn().getPawnType() == PawnType.KING) highlightTopRight(new BoardPosition(pos.x + 1, pos.y - 1));
		}
	}
	
	private void highlightBottomLeft(BoardPosition pos) {
		if(pos.x > 0 && pos.y + 1 < board.getHeight()) {
			setCellCornerColor(boardCells[pos.x - 1][pos.y + 1], board.getValue(pos.x - 1, pos.y + 1));
			if(board.getPawn().getPawnType() == PawnType.KING) highlightBottomLeft(new BoardPosition(pos.x - 1, pos.y + 1));
		}
	}
	
	private void highlightBottomRight(BoardPosition pos) {
		if(pos.x + 1 < board.getWidth() && pos.y + 1 < board.getHeight()) {
			setCellCornerColor(boardCells[pos.x + 1][pos.y + 1], board.getValue(pos.x + 1, pos.y + 1));
			if(board.getPawn().getPawnType() == PawnType.KING) highlightBottomRight(new BoardPosition(pos.x + 1, pos.y + 1));
		}
	}
	
	private void setCellCornerColor(DrawCell cell, int cellValue) {	
		if(cellValue == 1) cell.setColor(Color.GREEN);
		else cell.setColor(Color.RED);
	}
	
	private void highlightCaptures(BoardPosition pos) {
		if(canCaptureTopLeft(pos)) {
			boardCells[pos.x - 1][pos.y - 1].setColor(Color.BLUE);
			boardCells[pos.x - 2][pos.y - 2].setColor(Color.GREEN);
			++checkCaptures;
			
			if(checkCaptures < maxCapturesCheck) highlightCaptures(new BoardPosition(pos.x - 2, pos.y - 2));
		}
		
		if(canCaptureTopRight(pos)) {
			boardCells[pos.x + 1][pos.y - 1].setColor(Color.BLUE);
			boardCells[pos.x + 2][pos.y - 2].setColor(Color.GREEN);
			++checkCaptures;
			
			if(checkCaptures < maxCapturesCheck) highlightCaptures(new BoardPosition(pos.x + 2, pos.y - 2));
		}
		
		if(canCaptureBottomLeft(pos)) {
			boardCells[pos.x - 1][pos.y + 1].setColor(Color.BLUE);
			boardCells[pos.x - 2][pos.y + 2].setColor(Color.GREEN);
			++checkCaptures;
			
			if(checkCaptures < maxCapturesCheck) highlightCaptures(new BoardPosition(pos.x - 2, pos.y + 2));
		}
		
		if(canCaptureBottomRight(pos)) {
			boardCells[pos.x + 1][pos.y + 1].setColor(Color.BLUE);
			boardCells[pos.x + 2][pos.y + 2].setColor(Color.GREEN);
			++checkCaptures;
			
			if(checkCaptures < maxCapturesCheck) highlightCaptures(new BoardPosition(pos.x + 2, pos.y + 2));
		}
	}
	
	private boolean canCaptureTopLeft(BoardPosition pos) {
		return board.canCapture(new BoardPosition(pos.x - 1, pos.y - 1), new BoardPosition(pos.x - 2, pos.y - 2));
	}
	
	private boolean canCaptureTopRight(BoardPosition pos) {
		return board.canCapture(new BoardPosition(pos.x + 1, pos.y - 1), new BoardPosition(pos.x + 2, pos.y - 2));
	}
	
	private boolean canCaptureBottomLeft(BoardPosition pos) {
		return board.canCapture(new BoardPosition(pos.x - 1, pos.y + 1), new BoardPosition(pos.x - 2, pos.y + 2));
	}
	
	private boolean canCaptureBottomRight(BoardPosition pos) {
		return board.canCapture(new BoardPosition(pos.x + 1, pos.y + 1), new BoardPosition(pos.x + 2, pos.y + 2));
	}
}

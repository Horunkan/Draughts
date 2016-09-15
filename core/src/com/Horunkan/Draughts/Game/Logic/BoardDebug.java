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
	public boolean debug = false;
	
	protected DrawCell[][] boardCells;
	protected DrawPawn[] pawnsBright, pawnsDark;
	protected GameScreen screen;
	
	private Board board;
	private SpriteBatch batch;
	private BitmapFont font;
	
	public void debug(GameScreen screen, Board board, DrawCell[][] cells, DrawPawn[] pawnsBright, DrawPawn[] pawnsDark) {
		debug = true;
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
			BoardPosition pawnPosition = board.getPawn().getBoardPosition();
			boardCells[pawnPosition.x][pawnPosition.y].setColor(Color.CYAN); //Highlight cell where pawn is selected
			
			//Highlight possible movement
			
			//Left
			if(pawnPosition.x > 0) {
				if(board.getValue(pawnPosition.x - 1, pawnPosition.y) == 0) boardCells[pawnPosition.x - 1][pawnPosition.y].setColor(Color.RED);
			}
			
			//Right
			if(pawnPosition.x + 1 < board.getWidth()) {
				if(board.getValue(pawnPosition.x + 1, pawnPosition.y) == 0) boardCells[pawnPosition.x + 1][pawnPosition.y].setColor(Color.RED);
			}
			
			//Up
			if(pawnPosition.y > 0) {
				if(board.getValue(pawnPosition.x, pawnPosition.y - 1) == 0) boardCells[pawnPosition.x][pawnPosition.y - 1].setColor(Color.RED);
			}
			
			//Down
			if(pawnPosition.y + 1 < board.getHeight()) {
				if(board.getValue(pawnPosition.x, pawnPosition.y + 1) == 0) boardCells[pawnPosition.x][pawnPosition.y + 1].setColor(Color.RED);
			}
			
			//Top left corner
			if(pawnPosition.x > 0 && pawnPosition.y > 0) {
				if(board.getValue(pawnPosition.x - 1, pawnPosition.y - 1) == 0) boardCells[pawnPosition.x - 1][pawnPosition.y - 1].setColor(Color.RED);
				else if(board.getValue(pawnPosition.x - 1, pawnPosition.y - 1) == 1) boardCells[pawnPosition.x - 1][pawnPosition.y - 1].setColor(Color.GREEN);
				else if(board.getValue(pawnPosition.x - 1, pawnPosition.y - 1) == 2 && board.getPawn().getPawnType() == 2) boardCells[pawnPosition.x - 1][pawnPosition.y - 1].setColor(Color.RED);
				else if(board.getValue(pawnPosition.x - 1, pawnPosition.y - 1) == 2 && board.getPawn().getPawnType() == 3) boardCells[pawnPosition.x - 1][pawnPosition.y - 1].setColor(Color.MAGENTA);
				else if(board.getValue(pawnPosition.x - 1, pawnPosition.y - 1) == 3 && board.getPawn().getPawnType() == 3) boardCells[pawnPosition.x - 1][pawnPosition.y - 1].setColor(Color.RED);
				else if(board.getValue(pawnPosition.x - 1, pawnPosition.y - 1) == 3 && board.getPawn().getPawnType() == 2) boardCells[pawnPosition.x - 1][pawnPosition.y - 1].setColor(Color.MAGENTA);
			}
			
			//Top right corner
			if(pawnPosition.x + 1 < board.getWidth() && pawnPosition.y > 0) {
				if(board.getValue(pawnPosition.x + 1, pawnPosition.y - 1) == 0) boardCells[pawnPosition.x + 1][pawnPosition.y - 1].setColor(Color.RED);
				else if(board.getValue(pawnPosition.x + 1, pawnPosition.y - 1) == 1) boardCells[pawnPosition.x + 1][pawnPosition.y - 1].setColor(Color.GREEN);
				else if(board.getValue(pawnPosition.x + 1, pawnPosition.y - 1) == 2 && board.getPawn().getPawnType() == 2) boardCells[pawnPosition.x + 1][pawnPosition.y - 1].setColor(Color.RED);
				else if(board.getValue(pawnPosition.x + 1, pawnPosition.y - 1) == 2 && board.getPawn().getPawnType() == 3) boardCells[pawnPosition.x + 1][pawnPosition.y - 1].setColor(Color.MAGENTA);
				else if(board.getValue(pawnPosition.x + 1, pawnPosition.y - 1) == 3 && board.getPawn().getPawnType() == 3) boardCells[pawnPosition.x + 1][pawnPosition.y - 1].setColor(Color.RED);
				else if(board.getValue(pawnPosition.x + 1, pawnPosition.y - 1) == 3 && board.getPawn().getPawnType() == 2) boardCells[pawnPosition.x + 1][pawnPosition.y - 1].setColor(Color.MAGENTA);
			}
			
			//Bottom left corner
			if(pawnPosition.x > 0 && pawnPosition.y + 1 < board.getHeight()) {
				if(board.getValue(pawnPosition.x - 1, pawnPosition.y + 1) == 0) boardCells[pawnPosition.x - 1][pawnPosition.y + 1].setColor(Color.RED);
				else if(board.getValue(pawnPosition.x - 1, pawnPosition.y + 1) == 1) boardCells[pawnPosition.x - 1][pawnPosition.y + 1].setColor(Color.GREEN);
				else if(board.getValue(pawnPosition.x - 1, pawnPosition.y + 1) == 2 && board.getPawn().getPawnType() == 2) boardCells[pawnPosition.x - 1][pawnPosition.y + 1].setColor(Color.RED);
				else if(board.getValue(pawnPosition.x - 1, pawnPosition.y + 1) == 2 && board.getPawn().getPawnType() == 3) boardCells[pawnPosition.x - 1][pawnPosition.y + 1].setColor(Color.MAGENTA);
				else if(board.getValue(pawnPosition.x - 1, pawnPosition.y + 1) == 3 && board.getPawn().getPawnType() == 3) boardCells[pawnPosition.x - 1][pawnPosition.y + 1].setColor(Color.RED);
				else if(board.getValue(pawnPosition.x - 1, pawnPosition.y + 1) == 3 && board.getPawn().getPawnType() == 2) boardCells[pawnPosition.x - 1][pawnPosition.y + 1].setColor(Color.MAGENTA);
			}
			
			//Bottom right corner
			if(pawnPosition.x + 1 < board.getWidth() && pawnPosition.y + 1 < board.getHeight()) {
				if(board.getValue(pawnPosition.x + 1, pawnPosition.y + 1) == 0) boardCells[pawnPosition.x + 1][pawnPosition.y + 1].setColor(Color.RED);
				else if(board.getValue(pawnPosition.x + 1, pawnPosition.y + 1) == 1) boardCells[pawnPosition.x + 1][pawnPosition.y + 1].setColor(Color.GREEN);
				else if(board.getValue(pawnPosition.x + 1, pawnPosition.y + 1) == 2 && board.getPawn().getPawnType() == 2) boardCells[pawnPosition.x + 1][pawnPosition.y + 1].setColor(Color.RED);
				else if(board.getValue(pawnPosition.x + 1, pawnPosition.y + 1) == 2 && board.getPawn().getPawnType() == 3) boardCells[pawnPosition.x + 1][pawnPosition.y + 1].setColor(Color.MAGENTA);
				else if(board.getValue(pawnPosition.x + 1, pawnPosition.y + 1) == 3 && board.getPawn().getPawnType() == 3) boardCells[pawnPosition.x + 1][pawnPosition.y + 1].setColor(Color.RED);
				else if(board.getValue(pawnPosition.x + 1, pawnPosition.y + 1) == 3 && board.getPawn().getPawnType() == 2) boardCells[pawnPosition.x + 1][pawnPosition.y + 1].setColor(Color.MAGENTA);
			}
		}
	}
	
	private void drawBoardState() {
		for(int y = 0; y < board.getHeight(); ++y) {
			for(int x = 0; x < board.getWidth(); ++x) {
				font.draw(batch, Integer.toString(board.getValue(x, y)), boardCells[x][y].getPosition().x + 2, boardCells[x][y].getPosition().y + 63);
			}
		}
	}
}

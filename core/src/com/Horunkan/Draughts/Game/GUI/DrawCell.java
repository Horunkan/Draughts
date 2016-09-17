package com.Horunkan.Draughts.Game.GUI;

import com.Horunkan.Draughts.BoardPosition;
import com.Horunkan.Draughts.Game.Logic.Board;
import com.Horunkan.Draughts.Game.Logic.Board.CaptureDirection;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class DrawCell extends Image {
	private final BoardPosition pos;
	private Board board;
	
	public DrawCell(Skin skin, Board board, int cellType, int posX, int posY) {
		pos = new BoardPosition(posX, posY);
		this.board = board;
		if(cellType == 0) this.setDrawable(skin, "boardBright");
		else this.setDrawable(skin, "boardDark");
		
		this.addListener(new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) { return touched(); }
		});
	}
	
	public Vector2 getPosition() { return this.localToParentCoordinates(new Vector2(0,0)); }	
	public BoardPosition getBoardPosition() { return pos; }
	
	private boolean touched() {
		if(board.canMove(this)) {
    		//System.out.println("Moved pawn to position: " + pos);
    		board.movePawn(getPosition(), pos.x, pos.y);
    		board.unselectPawn();
    	}
		else {
			CaptureDirection dir = board.canCapture();
			
			if(dir != CaptureDirection.NO_CAPTURE) {
				//System.out.println("Pawn captured and moved to position: " + pos);
				board.movePawn(getPosition(), pos.x, pos.y);
				board.capture(dir);
				if(board.canCapture() == CaptureDirection.NO_CAPTURE) board.unselectPawn();
				board.updatePawnColor();
			}
		}		
		return false;
	}
}

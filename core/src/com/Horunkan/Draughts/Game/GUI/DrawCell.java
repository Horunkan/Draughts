package com.Horunkan.Draughts.Game.GUI;

import com.Horunkan.Draughts.Game.Logic.Board;
import com.Horunkan.Draughts.Game.Logic.Board.CaptureDirection;
import com.Horunkan.Draughts.Utilities.BoardPosition;
import com.Horunkan.Draughts.Utilities.TextureLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class DrawCell extends Image {
	private final BoardPosition pos;
	private final Board board;
	
	public DrawCell(Board board, int cellType, int posX, int posY) {
		pos = new BoardPosition(posX, posY);
		this.board = board;
		TextureLoader textures = TextureLoader.getInstace();
		
		if(cellType == 0) this.setDrawable(textures.getDrawable("boardBright"));
		else this.setDrawable(textures.getDrawable("boardDark"));
		
		this.addListener(new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) { return touched(); }
		});
	}
	
	private boolean touched() {
		if(board.canMove(this)) {
			System.out.println("Moved pawn to position: " + pos);
			board.movePawn(getPosition(), pos.x, pos.y);
			board.unselectPawn();
			board.changePlayer();
		}
		else if(board.canCapture(this)) {
			System.out.println("Pawn captured");
			CaptureDirection dir = CaptureDirection.NO_CAPTURE;
			BoardPosition direction = new BoardPosition(pos.x - board.getPawn().getBoardPosition().x, pos.y - board.getPawn().getBoardPosition().y);
			
			if(direction.x < 0 && direction.y < 0) dir = CaptureDirection.BOTTOM_RIGHT;
			if(direction.x > 0 && direction.y < 0) dir = CaptureDirection.BOTTOM_LEFT;
			if(direction.x < 0 && direction.y > 0) dir = CaptureDirection.TOP_RIGHT;
			if(direction.x > 0 && direction.y > 0) dir = CaptureDirection.TOP_LEFT;
						
			board.capture(dir, pos);
			board.movePawn(getPosition(), pos.x, pos.y);
			
			if(board.getCaptureDirection(board.getPawn().getBoardPosition()) == CaptureDirection.NO_CAPTURE) {
				board.unselectPawn();
				board.changePlayer();
			}
		}
		return false;
	}
	
	public Vector2 getPosition() { return this.localToParentCoordinates(new Vector2(0,0)); }	
	public BoardPosition getBoardPosition() { return pos; }
}

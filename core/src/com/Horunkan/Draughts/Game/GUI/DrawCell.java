package com.Horunkan.Draughts.Game.GUI;

import com.Horunkan.Draughts.Game.Logic.ActivePawn;
import com.Horunkan.Draughts.Game.Logic.ActivePawn.CaptureDirection;
import com.Horunkan.Draughts.Game.Logic.Player;
import com.Horunkan.Draughts.Utilities.BoardPosition;
import com.Horunkan.Draughts.Utilities.TextureLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class DrawCell extends Image {
	private final BoardPosition boardPos;
	
	public DrawCell(int cellType, int posX, int posY) {
		boardPos = new BoardPosition(posX, posY);
		
		if(cellType == 0) this.setDrawable(TextureLoader.getDrawable("boardBright"));
		else {
			this.setDrawable(TextureLoader.getDrawable("boardDark"));
			
			this.addListener(new InputListener() {
		        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) { return touched(); }
			});
		}
	}
	
	private boolean touched() {
		if(ActivePawn.isSelected()) {
			if(ActivePawn.canMove(this)) {
				System.out.println("Moved pawn to position: " + boardPos);
				ActivePawn.move(getPosition(), boardPos);
				ActivePawn.unselect();
				Player.change();
			}
			else if(ActivePawn.canCapturePawn(boardPos)) {
				System.out.println("Pawn captured");
				CaptureDirection dir = CaptureDirection.NO_CAPTURE;
				BoardPosition direction = new BoardPosition(boardPos.x - ActivePawn.get().getBoardPosition().x, boardPos.y - ActivePawn.get().getBoardPosition().y);
				
				if(direction.x < 0 && direction.y < 0) dir = CaptureDirection.BOTTOM_RIGHT;
				if(direction.x > 0 && direction.y < 0) dir = CaptureDirection.BOTTOM_LEFT;
				if(direction.x < 0 && direction.y > 0) dir = CaptureDirection.TOP_RIGHT;
				if(direction.x > 0 && direction.y > 0) dir = CaptureDirection.TOP_LEFT;
							
				ActivePawn.capture(dir, boardPos);
				ActivePawn.move(getPosition(), boardPos);
				
				if(ActivePawn.getCaptureDirection(ActivePawn.get().getBoardPosition()) == CaptureDirection.NO_CAPTURE) {
					ActivePawn.unselect();
					Player.change();
				}
			}
		}	
		return false;
	}
	
	public Vector2 getPosition() { return this.localToParentCoordinates(new Vector2(0,0)); }	
	public BoardPosition getBoardPosition() { return boardPos; }
}

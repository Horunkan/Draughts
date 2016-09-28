package com.Horunkan.Draughts.Game.GUI;

import com.Horunkan.Draughts.Game.Logic.ActivePawn;
import com.Horunkan.Draughts.Game.Logic.Player;
import com.Horunkan.Draughts.Utilities.BoardPosition;
import com.Horunkan.Draughts.Utilities.TextureLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class DrawCell extends Image {
	private final BoardPosition boardPos;
	private final Player player;
	
	public DrawCell(Player player, int cellType, int posX, int posY) {
		boardPos = new BoardPosition(posX, posY);
		this.player = player;
		
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
				player.change();
			}
			else if(ActivePawn.canCapturePawn(boardPos)) {
				System.out.println("Moved pawn to position: " + boardPos + " - PAWN CAPTURED");
				ActivePawn.captureAndMove(getPosition(), boardPos);
				
				if(!ActivePawn.anyCapturesLeft()) {
					ActivePawn.unselect();
					player.change();
				}
			}
		}	
		return false;
	}
	
	public Vector2 getPosition() { return this.localToParentCoordinates(new Vector2(0,0)); }	
	public BoardPosition getBoardPosition() { return boardPos; }
}

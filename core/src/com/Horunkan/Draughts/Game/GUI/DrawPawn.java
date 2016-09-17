package com.Horunkan.Draughts.Game.GUI;

import com.Horunkan.Draughts.BoardPosition;
import com.Horunkan.Draughts.Game.Logic.Board;
import com.Horunkan.Draughts.Game.Logic.Board.PawnType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class DrawPawn extends Image {
	private int pawnType;
	private BoardPosition pos;
	
	private final PawnType type;
	
	public DrawPawn(Skin skin, Board board, int pawnType, int posX, int posY) {
		if(pawnType == 2) {
			type = PawnType.PLAYER_A;
			this.setDrawable(skin, "pawnBright");
		}
		else {
			type = PawnType.PLAYER_B;
			this.setDrawable(skin, "pawnDark");
		}
		
		
		
		
		
		this.pawnType = pawnType;
		pos = new BoardPosition(posX, posY);
		//if(pawnType == 2) this.setDrawable(skin, "pawnBright");
		//else if(pawnType == 3) this.setDrawable(skin, "pawnDark");
		this.setSize(65, 65);
		
		this.addListener(new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	        	System.out.println("Pressed pawn on position: " + pos);
	        	
	        	if(board.getPawn() == getPawn()) board.unselectPawn();
	        	else {
	        		if(board.getPawn() != null) board.unselectPawn();
	        		board.setActivePawn(getPawn());
	        	}
                return false;
	        }
		});
	}
		
	public void setBoardPosition(int x, int y) { pos.setPosition(x, y); }
	public BoardPosition getBoardPosition() { return pos; }
	public int getPawnType() { return type.ordinal() + 2; }
		
	private DrawPawn getPawn() { return this; }
}

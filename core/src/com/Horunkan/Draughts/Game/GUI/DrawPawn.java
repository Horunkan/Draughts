package com.Horunkan.Draughts.Game.GUI;

import com.Horunkan.Draughts.Game.Logic.Board;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class DrawPawn extends Image {
	private int posX, posY, pawnType;
	
	public DrawPawn(Skin skin, Board board, int pawnType, int posX, int posY) {
		this.pawnType = pawnType;
		setPositionOnBoard(posX, posY);
		if(pawnType == 2) this.setDrawable(skin, "pawnBright");
		else if(pawnType == 3) this.setDrawable(skin, "pawnDark");
		this.setSize(65, 65);
		
		this.addListener(new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	        	System.out.println("Pressed pawn on position: [" + posX + "," + posY + "]");
	        	
	        	if(board.getPawn() == getPawn()) board.unselectPawn();
	        	else {
	        		if(board.getPawn() != null) board.unselectPawn();
	        		board.setActivePawn(getPawn());
	        	}
                return false;
	        }
		});
	}
	
	public void setPositionOnBoard(int x, int y) {
		posX = x;
		posY = y;
	}
	
	public int getBoardPositionX() { return posX; }
	public int getBoardPositionY() { return posY; }
	public int getPawnType() { return pawnType; }
		
	private DrawPawn getPawn() { return this; }
}

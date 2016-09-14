package com.Horunkan.Draughts.Game.GUI;

import com.Horunkan.Draughts.Game.Logic.Board;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class DrawCell extends Image {
	private final int posX, posY;
	
	public DrawCell(Skin skin, Board board, int cellType, int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		if(cellType == 0) this.setDrawable(skin, "boardBright");
		else this.setDrawable(skin, "boardDark");
		
		this.addListener(new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	        	if(board.canMove(getCell())) {
	        		System.out.println("Moved pawn to position: [" + posX + "," + posY + "]");
	        		board.movePawn(getPosition(), posX, posY);
	        		board.unselectPawn();
	        	}
                return false;
	        }
		});
	}
	
	public Vector2 getPosition() { return this.localToParentCoordinates(new Vector2(0,0)); }
	public int getBoardPositionX() { return posX; }
	public int getBoardPositionY() { return posY; }
	
	private DrawCell getCell() { return this; }
}

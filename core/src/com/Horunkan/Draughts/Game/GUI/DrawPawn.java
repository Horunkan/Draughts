package com.Horunkan.Draughts.Game.GUI;

import com.Horunkan.Draughts.Game.Logic.Board;
import com.Horunkan.Draughts.Game.Logic.Board.Player;
import com.Horunkan.Draughts.Utilities.BoardPosition;
import com.Horunkan.Draughts.Utilities.TextureLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class DrawPawn extends Image {
	private final int pawnType;
	private final Board board;
	private BoardPosition pos;
	private Player player;
	
	public DrawPawn(Board board, int pawnType, int posX, int posY) {
		this.board = board;
		this.pawnType = pawnType;
		pos = new BoardPosition(posX, posY);
		TextureLoader textures = TextureLoader.getInstace();
		
		if(pawnType == 2) {
			this.setDrawable(textures.getDrawable("pawnBright"));
			player = Player.BRIGHT;
		}
		else if(pawnType == 3) {
			this.setDrawable(textures.getDrawable("pawnDark"));
			player = Player.DARK;
		}
		this.setSize(65, 65);
		
		this.addListener(new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) { return touched(); }
		});
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if(this.getActions().size > 0) this.act(Gdx.graphics.getDeltaTime());
	}
		
	private boolean touched() {
		if(board.getActivePlayer() == player) {
			System.out.println("Pressed pawn on position: " + pos);
			if(board.getPawn() == this) board.unselectPawn();
			else {
				board.unselectPawn();
	    		board.setActivePawn(this);
			}
		}
		return false;
	}
		
	public void setBoardPosition(int x, int y) { pos.setPosition(x, y); }
	public BoardPosition getBoardPosition() { return pos; }
	public int getPawnType() { return pawnType; }
}

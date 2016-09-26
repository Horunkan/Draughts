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
	public enum PawnType {STANDARD, KING}
	
	private final Board board;
	private BoardPosition pos;
	private Player player;
	private PawnType type;
	private TextureLoader textures;
	
	public DrawPawn(Board board, int pawnType, int posX, int posY) {
		this.board = board;
		pos = new BoardPosition(posX, posY);
		textures = TextureLoader.getInstace();
		
		if(pawnType == 2) {
			this.setDrawable(textures.getDrawable("pawnBright"));
			player = Player.BRIGHT;
			type = PawnType.STANDARD;
		}
		else if(pawnType == 3) {
			this.setDrawable(textures.getDrawable("pawnDark"));
			player = Player.DARK;
			type = PawnType.STANDARD;
		}
		else if(pawnType == 4) {
			this.setDrawable(textures.getDrawable("pawnBrightKing"));
			player = Player.BRIGHT;
			type = PawnType.KING;
		}
		else if(pawnType == 5) {
			this.setDrawable(textures.getDrawable("pawnDarkKing"));
			player = Player.DARK;
			type = PawnType.KING;
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
	    		board.selectPawn(this);
			}
		}
		return false;
	}
	
	public void setAsKing() {
		type = PawnType.KING;
		if(player == Player.BRIGHT) this.setDrawable(textures.getDrawable("pawnBrightKing"));
		else this.setDrawable(textures.getDrawable("pawnDarkKing"));
	}
		
	public void setBoardPosition(int x, int y) { pos.setPosition(x, y); }
	public BoardPosition getBoardPosition() { return pos; }
	public Player getPawnPlayer() { return player; }
	public PawnType getPawnType() { return type; }
	
	public int getPawnPlayerInt() {
		if(player == Player.BRIGHT) return 2;
		else return 3;
	}
}

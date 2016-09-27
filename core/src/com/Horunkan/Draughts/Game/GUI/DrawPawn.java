package com.Horunkan.Draughts.Game.GUI;

import com.Horunkan.Draughts.Game.Logic.ActivePawn;
import com.Horunkan.Draughts.Game.Logic.Player;
import com.Horunkan.Draughts.Game.Logic.Player.Players;
import com.Horunkan.Draughts.Utilities.BoardPosition;
import com.Horunkan.Draughts.Utilities.TextureLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class DrawPawn extends Image {
	public enum PawnType {STANDARD, KING}
	
	private BoardPosition pos;
	private Players player;
	private PawnType type;
	
	public DrawPawn(int pawnType, int posX, int posY) {
		pos = new BoardPosition(posX, posY);
		
		if(pawnType == 2) {
			this.setDrawable(TextureLoader.getDrawable("pawnBright"));
			player = Players.BRIGHT;
			type = PawnType.STANDARD;
		}
		else if(pawnType == 3) {
			this.setDrawable(TextureLoader.getDrawable("pawnDark"));
			player = Players.DARK;
			type = PawnType.STANDARD;
		}
		else if(pawnType == 4) {
			this.setDrawable(TextureLoader.getDrawable("pawnBrightKing"));
			player = Players.BRIGHT;
			type = PawnType.KING;
		}
		else if(pawnType == 5) {
			this.setDrawable(TextureLoader.getDrawable("pawnDarkKing"));
			player = Players.DARK;
			type = PawnType.KING;
		}
		
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
		if(Player.getActive() == player) {
			System.out.println("Pressed pawn on position: " + pos);
			if(ActivePawn.get() == this) ActivePawn.unselect();
			else {
				ActivePawn.unselect();
				ActivePawn.select(this);
			}
		}
		return false;
	}
	
	public void setAsKing() {
		type = PawnType.KING;
		if(player == Players.BRIGHT) this.setDrawable(TextureLoader.getDrawable("pawnBrightKing"));
		else this.setDrawable(TextureLoader.getDrawable("pawnDarkKing"));
	}
	
	public void setBoardPosition(BoardPosition pos) { setBoardPosition(pos.x, pos.y); }
	public void setBoardPosition(int x, int y) { pos.setPosition(x, y); }
	public BoardPosition getBoardPosition() { return pos; }
	public Players getPlayer() { return player; }
	public PawnType getType() { return type; }
	
	public int getPlayerInt() {
		if(player == Players.BRIGHT) return 2;
		else return 3;
	}
}

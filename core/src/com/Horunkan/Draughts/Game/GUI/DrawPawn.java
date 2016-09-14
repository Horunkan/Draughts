package com.Horunkan.Draughts.Game.GUI;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class DrawPawn extends Image {
	public DrawPawn(Skin skin, int pawnType, int posX, int posY) {
		if(pawnType == 2) this.setDrawable(skin, "pawnBright");
		else if(pawnType == 3) this.setDrawable(skin, "pawnDark");
		this.setSize(65, 65);
		
		this.addListener(new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	        	System.out.println("Pressed pawn on position: [" + posX + "," + posY + "]");
                return false;
	        }
		});
	}
}

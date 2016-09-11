package com.Horunkan.Draughts.Game.GUI;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class DrawPawn extends Image {
	public DrawPawn(Skin skin, int pawnType) {
		if(pawnType == 2) this.setDrawable(skin, "pawnBright");
		else if(pawnType == 3) this.setDrawable(skin, "pawnDark");
		this.setSize(65, 65);
	}
}

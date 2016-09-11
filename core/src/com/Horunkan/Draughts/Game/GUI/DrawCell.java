package com.Horunkan.Draughts.Game.GUI;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class DrawCell extends Image {
	public DrawCell(Skin skin, int cellType) {
		if(cellType == 0) this.setDrawable(skin, "boardBright");
		else this.setDrawable(skin, "boardDark");
	}
}

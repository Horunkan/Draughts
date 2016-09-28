package com.Horunkan.Draughts.NewGame.GUI;

import com.Horunkan.Draughts.Utilities.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class Title {
	private final int titleFontSize = 25;
	
	private LabelStyle style;
	private Label label;
	
	public Title(String title) {
		style = new LabelStyle();
		style.font = Font.get(titleFontSize);
		style.fontColor = Color.WHITE;
		
		label = new Label(title, style);
	}
	
	public Label get() { return label; }
}

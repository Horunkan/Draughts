package com.Horunkan.Draughts.Utilities;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class ButtonStyle extends TextButtonStyle {
	private static ButtonStyle instance = null;
	
	public static ButtonStyle getInstance() {
		if(instance == null) instance = new ButtonStyle();
		return instance;
	}
	
	private ButtonStyle() { 
		FontLoader font = FontLoader.getInstance();
		
		this.font = font.getFont(25);
		this.up = TextureLoader.getDrawable("buttonStandard");
		this.down = TextureLoader.getDrawable("buttonPressed");
	}
}

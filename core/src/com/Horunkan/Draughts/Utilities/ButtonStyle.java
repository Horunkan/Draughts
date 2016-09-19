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
		TextureLoader textures = TextureLoader.getInstace();
		
		this.font = font.getFont(25);
		this.up = textures.getDrawable("buttonStandard");
		this.down = textures.getDrawable("buttonPressed");
	}
}

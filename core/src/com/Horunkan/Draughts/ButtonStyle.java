package com.Horunkan.Draughts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class ButtonStyle extends TextButtonStyle {
	private static ButtonStyle instance = null;
	
	public static ButtonStyle getInstance() {
		if(instance == null) instance = new ButtonStyle();
		return instance;
	}
	
	private ButtonStyle() { 
		Skin skin = new Skin();
		skin.add("ButtonStandard", new Texture(Gdx.files.internal("buttonStandard.png")));
		skin.add("ButtonPressed", new Texture(Gdx.files.internal("buttonPressed.png")));
		
		FontLoader font = FontLoader.getInstance();
		
		this.font = font.getFont(25);
		this.up = skin.newDrawable("ButtonStandard");
		this.down = skin.newDrawable("ButtonPressed");
	}
}

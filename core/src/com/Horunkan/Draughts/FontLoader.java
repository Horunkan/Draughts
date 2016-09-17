package com.Horunkan.Draughts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class FontLoader {
	private static FontLoader instance = null;
	private final String FONT_NAME = "verdana.ttf";
	
	private FreeTypeFontGenerator generator;
	private FreeTypeFontParameter parameter;
	
	public static FontLoader getInstance() {
		if(instance == null) instance = new FontLoader();
		return instance;
	}
	
	public BitmapFont getFont(int size) {
		generator = new FreeTypeFontGenerator(Gdx.files.internal(FONT_NAME));
		parameter = new FreeTypeFontParameter();
		parameter.size = size;
		BitmapFont font = generator.generateFont(parameter);
		generator.dispose();
		
		return font;
	}
	
	private FontLoader() { }
}

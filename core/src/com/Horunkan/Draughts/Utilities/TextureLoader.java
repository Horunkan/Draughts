package com.Horunkan.Draughts.Utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class TextureLoader {
	private static TextureLoader instance = null;
	
	private Skin skin;
	
	public static TextureLoader getInstace() {
		if(instance == null) instance = new TextureLoader();
		return instance;
	}
	
	public Skin getSkin() { return skin; }
	public Drawable getDrawable(String name) { return skin.getDrawable(name); }
	
	private TextureLoader() {
		skin = new Skin();
		
		skin.add("ButtonStandard", new Texture(Gdx.files.internal("Textures/buttonStandard.png")));
		skin.add("ButtonPressed", new Texture(Gdx.files.internal("Textures/buttonPressed.png")));
		skin.add("boardBright", new Texture(Gdx.files.internal("Textures/boardCell_bright.png")));
		skin.add("boardDark", new Texture(Gdx.files.internal("Textures/boardCell_dark.png")));
		skin.add("pawnBright", new Texture(Gdx.files.internal("Textures/pawn_bright.png")));
		skin.add("pawnDark", new Texture(Gdx.files.internal("Textures/pawn_dark.png")));
		skin.add("pawnBrightKing", new Texture(Gdx.files.internal("Textures/pawn_bright_king.png")));
		skin.add("pawnDarkKing", new Texture(Gdx.files.internal("Textures/pawn_dark_king.png")));
	}
	
	
}

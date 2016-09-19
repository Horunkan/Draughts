package com.Horunkan.Draughts.Utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class TextureLoader {
	private static TextureLoader instance = null;
	
	private Skin skin;
	private TextureAtlas atlas;
	
	public static TextureLoader getInstace() {
		if(instance == null) instance = new TextureLoader();
		return instance;
	}
	
	public Skin getSkin() { return skin; }
	public Drawable getDrawable(String name) { return skin.getDrawable(name); }
	
	private TextureLoader() {
		atlas = new TextureAtlas(Gdx.files.internal("textures.atlas"));		
		skin = new Skin();
				
		skin.add("buttonStandard", atlas.createSprite("buttonStandard"));
		skin.add("buttonPressed", atlas.createSprite("buttonPressed"));
		skin.add("boardBright", atlas.createSprite("boardCell_bright"));
		skin.add("boardDark", atlas.createSprite("boardCell_dark"));
		skin.add("pawnBright", atlas.createSprite("pawn_bright"));
		skin.add("pawnDark", atlas.createSprite("pawn_dark"));
		skin.add("pawnBrightKing", atlas.createSprite("pawn_bright_king"));
		skin.add("pawnDarkKing", atlas.createSprite("pawn_dark_king"));
	}
}

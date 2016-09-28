package com.Horunkan.Draughts.Utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class TextureLoader {
	private static Skin skin;
	private static TextureAtlas atlas;
		
	public static Skin getSkin() {
		if(skin == null) new TextureLoader();
		return skin;
	}
	
	public static Drawable getDrawable(String name) {
		if(skin == null) new TextureLoader();
		return skin.getDrawable(name);
	}
	
	private TextureLoader() {
		atlas = new TextureAtlas(Gdx.files.internal("textures.atlas"));		
		skin = new Skin();
		skin.addRegions(atlas);
		skin.getRegion("pawn_bright").getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		skin.getRegion("pawn_dark").getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		skin.getRegion("pawn_bright_king").getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		skin.getRegion("pawn_dark_king").getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		
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

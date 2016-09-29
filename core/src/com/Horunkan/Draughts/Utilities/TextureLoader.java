package com.Horunkan.Draughts.Utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

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
	
	public static Drawable getDrawable(int width, int height, Color col) {
		Pixmap pixmap = new Pixmap(width, height, Format.RGBA8888);
		pixmap.setColor(col);
		pixmap.fill();
		Drawable drw = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
		pixmap.dispose();
				
		return drw;
	}
	
	private TextureLoader() {
		try {
			atlas = new TextureAtlas(Gdx.files.internal("textures.atlas"));		
		}
		catch(Exception ex) {
			System.err.println("Failed to load textures - " + ex.getMessage());
			ErrorCreateAtlas();
		}
		finally {
			createSkin();
		}
	}
	
	private void ErrorCreateAtlas() {
		Pixmap pixmap = new Pixmap(100, 100, Format.RGBA8888);
		pixmap.setColor(Color.LIGHT_GRAY);
		pixmap.fill();
		TextureRegion region = new TextureRegion(new Texture(pixmap));
		pixmap.dispose();
		
		atlas = new TextureAtlas();	
		atlas.addRegion("buttonStandard", region);
		atlas.addRegion("buttonPressed", region);
		atlas.addRegion("boardCell_bright", region);
		atlas.addRegion("boardCell_dark", region);
		atlas.addRegion("pawn_bright", region);
		atlas.addRegion("pawn_dark", region);
		atlas.addRegion("pawn_bright_king", region);
		atlas.addRegion("pawn_dark_king", region);
		atlas.addRegion("checkboxOn", region);
		atlas.addRegion("checkboxOff", region);
		atlas.addRegion("NewGame_background", region);
	}
	
	private void createSkin() {
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
		skin.add("checkboxOn", atlas.createSprite("checkboxOn"));
		skin.add("checkboxOff", atlas.createSprite("checkboxOff"));
		skin.add("NewGameBackground", atlas.createSprite("NewGame_background"));
	}
}

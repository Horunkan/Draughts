package com.Horunkan.Draughts.Views;

import com.Horunkan.Draughts.Draughts;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameScreen extends AbstractScreen {
	private Skin skin;

	public GameScreen(Draughts game) {
		super(game);
		skin = new Skin();
		loadTextures();
	}
	
	@Override
	public void render(float delta) {
		//Clear view
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) game.setScreen(Draughts.ScreenMode.MAIN_MENU);
		
		stage.draw();
	}
	
	private void loadTextures() {
		skin.add("boardBright", new Texture(Gdx.files.internal("Textures/boardCell_bright.png")));
		skin.add("boardDark", new Texture(Gdx.files.internal("Textures/boardCell_dark.png")));
		skin.add("pawnBright", new Texture(Gdx.files.internal("Textures/pawn_bright.png")));
		skin.add("pawnDark", new Texture(Gdx.files.internal("Textures/pawn_dark.png")));
	}
}

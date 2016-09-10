package com.Horunkan.Draughts.Views;

import com.Horunkan.Draughts.Draughts;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;

public class GameScreen extends AbstractScreen {

	public GameScreen(Draughts game) {
		super(game);
	}
	
	@Override
	public void render(float delta) {
		//Clear view
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) game.setScreen(Draughts.ScreenMode.MAIN_MENU);
		
		stage.draw();
	}
}

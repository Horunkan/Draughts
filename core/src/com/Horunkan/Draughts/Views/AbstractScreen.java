package com.Horunkan.Draughts.Views;

import com.Horunkan.Draughts.Draughts;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public abstract class AbstractScreen implements Screen {
	public OrthographicCamera camera;
	protected Stage stage;
	protected SpriteBatch spriteBatch;
	protected Draughts game;
	
	public AbstractScreen(Draughts game) {
		this.game = game;
		createCamera();
		
		stage = new Stage(new StretchViewport(Draughts.WIDTH, Draughts.HEIGHT, camera));
		spriteBatch = new SpriteBatch();
	}
	
	private void createCamera() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Draughts.WIDTH, Draughts.HEIGHT);
		camera.update();
	}
	
	protected void clearScreen() {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
		
	@Override public void show() { Gdx.input.setInputProcessor(stage); }
	@Override public void resize(int width, int height) { }
	@Override public void pause() { }
	@Override public void resume() { }
	@Override public void hide() { }
	
	@Override
	public void dispose() { 
		stage.dispose();
		spriteBatch.dispose();
	}
}

package com.Horunkan.Draughts.Views;

import com.Horunkan.Draughts.Draughts;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public abstract class AbstractScreen implements Screen {
	protected OrthographicCamera camera;
	protected Stage stage;
	protected SpriteBatch spriteBatch;
	
	public AbstractScreen() {
		createCamera();
		
		stage = new Stage(new StretchViewport(Draughts.WIDTH, Draughts.HEIGHT, camera));
		spriteBatch = new SpriteBatch();
	}
	
	private void createCamera() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Draughts.WIDTH, Draughts.HEIGHT);
		camera.update();
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

package com.Horunkan.Draughts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class MainMenu implements Screen {
	private OrthographicCamera camera;
	private Stage stage;
	
	public MainMenu() {
		//Create camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Draughts.WIDTH, Draughts.HEIGHT);
		camera.update();
		
		stage = new Stage(new StretchViewport(Draughts.WIDTH, Draughts.HEIGHT, camera));
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void render(float delta) {
		//Clear view
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
	}
	
	@Override
	public void show() { }

	@Override
	public void resize(int width, int height) { }

	@Override
	public void pause() { }

	@Override
	public void resume() { }

	@Override
	public void hide() { }

	@Override
	public void dispose() { }
}

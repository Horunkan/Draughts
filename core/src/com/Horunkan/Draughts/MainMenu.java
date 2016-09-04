package com.Horunkan.Draughts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
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
		
		FontLoader loader = FontLoader.getInstance();
		
		//Create white texture for button - TEMP
		Pixmap pixmap = new Pixmap(100, 100, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		
		Skin skin = new Skin();
		skin.add("white", new Texture(pixmap));
		
		//Create buttons
		TextButtonStyle style = new TextButtonStyle();
		style.font = loader.getFont(25);
		style.up = skin.newDrawable("white", Color.DARK_GRAY);
		style.down = skin.newDrawable("white", Color.LIGHT_GRAY);
		
		TextButton testButtonA = new TextButton("Test button 1", style);
		TextButton testButtonB = new TextButton("Test button 2", style);
		TextButton testButtonC = new TextButton("Test button 3", style);
		
		//Add buttons to scene
		Table container = new Table();
		container.setPosition(Draughts.WIDTH/2, Draughts.HEIGHT/2);
		//container.setDebug(true);
		
		container.add(testButtonA).size(250, 65);
		container.row().padTop(10);
		container.add(testButtonB).size(250, 65);
		container.row().padTop(10);
		container.add(testButtonC).size(250, 65);
		container.row().padTop(10);
	
		stage.addActor(container);
	}
	
	@Override
	public void render(float delta) {
		//Clear view
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.draw();
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

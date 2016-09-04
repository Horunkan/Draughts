package com.Horunkan.Draughts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class MainMenu implements Screen {
	private OrthographicCamera camera;
	private Stage stage;
	private ButtonStyle buttonStyle;
	private SpriteBatch spriteBatch;
	private Sprite logoSprite;
	
	public MainMenu() {
		//Create camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Draughts.WIDTH, Draughts.HEIGHT);
		camera.update();
		
		stage = new Stage(new StretchViewport(Draughts.WIDTH, Draughts.HEIGHT, camera));
		Gdx.input.setInputProcessor(stage);	
		
		//Buttons
		buttonStyle = ButtonStyle.getInstance();
		TextButton testButtonA = new TextButton("Test button 1", buttonStyle);
		TextButton testButtonB = new TextButton("Test button 2", buttonStyle);
		TextButton testButtonC = new TextButton("Test button 3", buttonStyle);
		
		//Logo
		Pixmap pixmap = new Pixmap( 500, 150, Format.RGBA8888 );
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
				
		logoSprite = new Sprite(new Texture(pixmap));
		spriteBatch = new SpriteBatch();
		logoSprite.setPosition(Draughts.WIDTH/2 - logoSprite.getWidth()/2, Draughts.HEIGHT/2 - logoSprite.getHeight()/2 + 150);
		
		//Add buttons to scene
		Table container = new Table();
		container.setPosition(Draughts.WIDTH/2, Draughts.HEIGHT/2 - 80);
		//container.setDebug(true);
		
		container.add(testButtonA).size(300, 87);
		container.row().padTop(5);
		container.add(testButtonB).size(300, 87);
		container.row().padTop(5);
		container.add(testButtonC).size(300, 87);
		container.row().padTop(5);
	
		stage.addActor(container);
	}
	
	@Override
	public void render(float delta) {
		//Clear view
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		spriteBatch.begin();
		logoSprite.draw(spriteBatch);
		spriteBatch.end();
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

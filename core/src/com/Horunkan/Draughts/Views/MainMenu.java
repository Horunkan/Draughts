package com.Horunkan.Draughts.Views;

import com.Horunkan.Draughts.ButtonStyle;
import com.Horunkan.Draughts.Draughts;
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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class MainMenu implements Screen {
	private OrthographicCamera camera;
	private Stage stage;
	private SpriteBatch spriteBatch;
	private Sprite logoSprite;
	private Table buttonContainer;
	private TextButton newGameButton, exitGameButton;
	
	public MainMenu() {
		//Create camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Draughts.WIDTH, Draughts.HEIGHT);
		camera.update();
		
		stage = new Stage(new StretchViewport(Draughts.WIDTH, Draughts.HEIGHT, camera));
		Gdx.input.setInputProcessor(stage);	
		spriteBatch = new SpriteBatch();
		
		createLogo();
		createButtons();
		createButtonsListeners();
		createButtonsContainer();

		stage.addActor(buttonContainer);
	}
	
	private void createLogo() {
		//Create texture [PLACEHOLDER]
		Pixmap pixmap = new Pixmap( 500, 150, Format.RGBA8888 );
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		
		logoSprite = new Sprite(new Texture(pixmap));
		logoSprite.setPosition(Draughts.WIDTH/2 - logoSprite.getWidth()/2, Draughts.HEIGHT/2 - logoSprite.getHeight()/2 + 150);
	}
	
	private void createButtonsContainer() {
		buttonContainer = new Table();
		buttonContainer.setPosition(Draughts.WIDTH/2, Draughts.HEIGHT/2 - 80);
		//container.setDebug(true);
		
		buttonContainer.add(newGameButton).size(300, 87);
		buttonContainer.row().padTop(5);
		buttonContainer.add(exitGameButton).size(300, 87);
	}
	
	private void createButtons() {
		ButtonStyle style = ButtonStyle.getInstance();
		
		newGameButton = new TextButton("New game", style);
		exitGameButton = new TextButton("Exit game", style);
	}
	
	private void createButtonsListeners() {
		newGameButton.addListener(new ChangeListener() {
	        @Override
	        public void changed (ChangeEvent event, Actor actor) {
	           System.out.println("New Game");
	        }
	    });
		
		exitGameButton.addListener(new ChangeListener() {
	        @Override
	        public void changed (ChangeEvent event, Actor actor) {
	            Gdx.app.exit();
	        }
	    });
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
	public void dispose() { 
		stage.dispose();
		spriteBatch.dispose();
	}
}

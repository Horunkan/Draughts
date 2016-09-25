package com.Horunkan.Draughts.Views;

import com.Horunkan.Draughts.Draughts;
import com.Horunkan.Draughts.Draughts.ScreenMode;
import com.Horunkan.Draughts.Utilities.ButtonStyle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MainMenu extends AbstractScreen {
	private Sprite gameLogo;
	private Table buttonContainer;
	private TextButton newGameButton, exitGameButton;
	
	public MainMenu(Draughts game) {
		super(game);

		createGameLogo();
		createButtons();
		createButtonsListeners();
		createButtonsContainer();

		stage.addActor(buttonContainer);
	}
	
	private void createGameLogo() {
		//TODO Create texture
		Pixmap pixmap = new Pixmap(500, 150, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		
		gameLogo = new Sprite(new Texture(pixmap));
		gameLogo.setPosition(Draughts.WIDTH/2 - gameLogo.getWidth()/2, Draughts.HEIGHT/2 - gameLogo.getHeight()/2 + 150);
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
			@Override public void changed (ChangeEvent event, Actor actor) {
	           game.setScreen(ScreenMode.NEW_GAME);
	        }
	    });
		
		exitGameButton.addListener(new ChangeListener() {
			@Override public void changed (ChangeEvent event, Actor actor) {
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
		gameLogo.draw(spriteBatch);
		spriteBatch.end();
		stage.draw();
	}
	
	@Override public void dispose() { 
		super.dispose();
	}
}

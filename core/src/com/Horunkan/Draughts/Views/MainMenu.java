package com.Horunkan.Draughts.Views;

import com.Horunkan.Draughts.Draughts;
import com.Horunkan.Draughts.Draughts.ScreenMode;
import com.Horunkan.Draughts.Utilities.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MainMenu extends AbstractScreen {
	private final int logoWidth = 500;
	private final int logoHeight = 150;
	private final int buttonFontSize = 25;
	private final float buttonWidth = 300;
	private final float buttonHeight = 87;
	
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
		gameLogo = new Sprite(new Texture(Gdx.files.internal("Textures/Logo.png")));
		gameLogo.setPosition(Draughts.WIDTH/2 - logoWidth/2, Draughts.HEIGHT/2 - logoHeight/2 + 100);
	}
	
	private void createButtonsContainer() {
		buttonContainer = new Table();
		buttonContainer.setPosition(Draughts.WIDTH/2, Draughts.HEIGHT/2 - 80);
		
		buttonContainer.add(newGameButton).size(buttonWidth, buttonHeight);
		buttonContainer.row().padTop(5);
		buttonContainer.add(exitGameButton).size(buttonWidth, buttonHeight);
	}
	
	private void createButtons() {
		TextButtonStyle style = new TextButtonStyle();
		style.font = Font.get(buttonFontSize);
		style.up = TextureLoader.getDrawable("buttonStandard");
		style.down = TextureLoader.getDrawable("buttonPressed");
		
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
		clearScreen();
		
		spriteBatch.begin();
		gameLogo.draw(spriteBatch);
		spriteBatch.end();
		stage.draw();
	}
	
	@Override public void dispose() { 
		super.dispose();
	}
}

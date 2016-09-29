package com.Horunkan.Draughts.Views;

import com.Horunkan.Draughts.Draughts;
import com.Horunkan.Draughts.Draughts.ScreenMode;
import com.Horunkan.Draughts.NewGame.GUI.*;
import com.Horunkan.Draughts.Utilities.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class NewGame extends AbstractScreen {
	private final float mainContainerWidth = 590;
	private final float mainContainerHeight = 455;
	private final int buttonFontSize = 25;
	private final float buttonWidth = 200;
	private final float buttonHeight = 58;
	
	private Table mainContainer;
	private SelectMode selectMode;
	private SelectBoard selectBoard;
	private SelectNames selectNames;
	private TextButton startGameButton, backToMenuButton;
	private Title selectModeTitle, selectBoardTitle, selectNamesTitle;

	//TODO Add background
	public NewGame(Draughts game) {
		super(game);
		createContainer();
		createButtons();
		createButtonsListeners();
		
		selectModeTitle = new Title("Select mode");
		mainContainer.add(selectModeTitle.get()).colspan(2).row();
		addSelectMode();
		
		
		selectBoardTitle = new Title("Select board");
		selectNamesTitle = new Title("Select names");
		mainContainer.add(selectBoardTitle.get()).pad(5);
		mainContainer.add(selectNamesTitle.get()).pad(5).row();
		addSelectBoard();
		addSelectNames();
		
		mainContainer.add(backToMenuButton).size(buttonWidth, buttonHeight).pad(5);
		mainContainer.add(startGameButton).size(buttonWidth, buttonHeight).pad(5);
			
		stage.addActor(mainContainer);
	}

	@Override
	public void render(float delta) {
		clearScreen();
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) game.setScreen(Draughts.ScreenMode.MAIN_MENU);
		
		stage.act(delta);
		stage.draw();
	}
	
	public String[] getPlayerNames() { return selectNames.getNames(); }
	public String getBoardName() { return selectBoard.getSelectedBoard(); }
	
	private void createContainer() {
		mainContainer = new Table();
		mainContainer.setSize(mainContainerWidth, mainContainerHeight);
		mainContainer.setPosition(Draughts.WIDTH/2 - mainContainerWidth/2, Draughts.HEIGHT/2 - mainContainerHeight/2);
		//mainContainer.debug();
		//TODO Create texture
		Pixmap pixmap = new Pixmap((int)mainContainerWidth, (int)mainContainerHeight, Format.RGBA8888);
		pixmap.setColor(0.152f, 0.152f, 0.152f, 1);
		pixmap.fill();
		
		mainContainer.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(pixmap))));
	}
	
	private void createButtons() {
		TextButtonStyle style = new TextButtonStyle();
		style.font = Font.get(buttonFontSize);
		style.up = TextureLoader.getDrawable("buttonStandard");
		style.down = TextureLoader.getDrawable("buttonPressed");
		
		startGameButton = new TextButton("Start game", style);
		backToMenuButton = new TextButton("Back", style);
	}
	
	private void createButtonsListeners() {
		startGameButton.addListener(new ChangeListener() {
			@Override public void changed (ChangeEvent event, Actor actor) {
	           game.setScreen(ScreenMode.GAME);
	        }
	    });
		
		backToMenuButton.addListener(new ChangeListener() {
			@Override public void changed (ChangeEvent event, Actor actor) {
	            game.setScreen(ScreenMode.MAIN_MENU);
	        }
	    });
	}
	
	private void addSelectMode() {
		selectMode = new SelectMode();
		selectMode.addToTable(mainContainer);
		mainContainer.row();
	}
	
	private void addSelectBoard() {
		selectBoard = new SelectBoard();
		selectBoard.addToTable(mainContainer);
	}
	
	private void addSelectNames() {
		selectNames = new SelectNames();
		selectNames.addToTable(mainContainer);
		mainContainer.row();
	}
}

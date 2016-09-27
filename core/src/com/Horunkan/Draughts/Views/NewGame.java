package com.Horunkan.Draughts.Views;

import com.Horunkan.Draughts.Draughts;
import com.Horunkan.Draughts.Draughts.ScreenMode;
import com.Horunkan.Draughts.NewGame.GUI.*;
import com.Horunkan.Draughts.Utilities.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

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
		createButtons();
		createButtonsListeners();
		
		mainContainer = new Table();
		mainContainer.setSize(mainContainerWidth, mainContainerHeight);
		mainContainer.setPosition(Draughts.WIDTH/2 - mainContainerWidth/2, Draughts.HEIGHT/2 - mainContainerHeight/2);
		mainContainer.debug();
		
		selectModeTitle = new Title("Select mode");
		mainContainer.add(selectModeTitle.get()).colspan(2).row();
		
		selectMode = new SelectMode();
		selectMode.addToTable(mainContainer);
		mainContainer.row();
		
		selectBoardTitle = new Title("Select board");
		selectNamesTitle = new Title("Select names");
		mainContainer.add(selectBoardTitle.get()).pad(5);
		mainContainer.add(selectNamesTitle.get()).pad(5).row();
		
		selectBoard = new SelectBoard();
		selectBoard.addToTable(mainContainer);
		
		selectNames = new SelectNames();
		selectNames.addToTable(mainContainer);
		mainContainer.row();
		
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
}

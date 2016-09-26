package com.Horunkan.Draughts.Views;

import com.Horunkan.Draughts.Draughts;
import com.Horunkan.Draughts.Draughts.ScreenMode;
import com.Horunkan.Draughts.NewGame.GUI.SelectBoard;
import com.Horunkan.Draughts.NewGame.GUI.SelectMode;
import com.Horunkan.Draughts.NewGame.GUI.SelectNames;
import com.Horunkan.Draughts.NewGame.GUI.Title;
import com.Horunkan.Draughts.Utilities.ButtonStyle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class NewGame extends AbstractScreen {
	private final float mainContainerWidth = 590;
	private final float mainContainerHeight = 455;
	
	private Table mainContainer;
	private SelectMode selectMode;
	private SelectBoard selectBoard;
	private SelectNames selectNames;
	private TextButton startGameButton, backToMenuButton;
	private Title selectModeTitle, selectBoardTitle, selectNamesTitle;

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
		
		mainContainer.add(backToMenuButton).size(200, 58).pad(5);
		mainContainer.add(startGameButton).size(200, 58).pad(5);
			
		stage.addActor(mainContainer);
		System.out.println(mainContainer.getMinHeight());
		System.out.println(mainContainer.getMinWidth());
	}

	@Override
	public void render(float delta) {
		//Clear view
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		stage.draw();
	}
	
	public String[] getPlayerNames() { return selectNames.getNames(); }
	public String getBoardName() { return selectBoard.getSelectedBoard(); }
	
	private void createButtons() {
		ButtonStyle style = ButtonStyle.getInstance();
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

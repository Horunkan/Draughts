package com.Horunkan.Draughts.Views;

import com.Horunkan.Draughts.Draughts;
import com.Horunkan.Draughts.NewGame.GUI.SelectBoard;
import com.Horunkan.Draughts.NewGame.GUI.SelectMode;
import com.Horunkan.Draughts.NewGame.GUI.SelectNames;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class NewGame extends AbstractScreen {
	private Table mainContainer;
	private SelectMode selectMode;
	private SelectBoard selectBoard;
	private SelectNames selectNames;

	public NewGame(Draughts game) {
		super(game);
		
		mainContainer = new Table();
		mainContainer.setFillParent(true);
		mainContainer.debug();
		
		selectMode = new SelectMode();
		selectMode.addToTable(mainContainer);
		mainContainer.row();
		
		selectBoard = new SelectBoard();
		selectBoard.addToTable(mainContainer);
		
		selectNames = new SelectNames();
		selectNames.addToTable(mainContainer);
			
		stage.addActor(mainContainer);
	}

	@Override
	public void render(float delta) {
		//Clear view
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
	}
	
	public Table getContainer() { return mainContainer; }
}

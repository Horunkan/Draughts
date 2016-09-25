package com.Horunkan.Draughts.Views;

import com.Horunkan.Draughts.Draughts;
import com.Horunkan.Draughts.NewGame.GUI.SelectMode;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class NewGame extends AbstractScreen {
	private Table mainContainer;
	private SelectMode selectMode;

	public NewGame(Draughts game) {
		super(game);
		
		mainContainer = new Table();
		mainContainer.setFillParent(true);
		mainContainer.debug();
		
		selectMode = new SelectMode();
		selectMode.addToTable(mainContainer);
			
		stage.addActor(mainContainer);
	}

	@Override
	public void render(float delta) {
		//Clear view
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.draw();
	}
	
	public Table getContainer() { return mainContainer; }
}

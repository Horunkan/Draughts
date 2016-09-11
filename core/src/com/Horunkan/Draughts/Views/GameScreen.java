package com.Horunkan.Draughts.Views;

import com.Horunkan.Draughts.Draughts;
import com.Horunkan.Draughts.Game.GUI.DrawCell;
import com.Horunkan.Draughts.Game.Logic.Board;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class GameScreen extends AbstractScreen {
	private Skin skin;
	private Table boardCellContainer;
	private Board board;
	private DrawCell[][] boardCells;

	public GameScreen(Draughts game) {
		super(game);
		skin = new Skin();
		loadTextures();
		
		boardCellContainer = new Table();
		boardCellContainer.setPosition(Draughts.WIDTH/2, Draughts.HEIGHT/2);
		board = new Board();
		loadBoard();
		
		stage.addActor(boardCellContainer);
	}
	
	@Override
	public void render(float delta) {
		//Clear view
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) game.setScreen(Draughts.ScreenMode.MAIN_MENU);
		
		stage.draw();
	}
	
	private void loadTextures() {
		skin.add("boardBright", new Texture(Gdx.files.internal("Textures/boardCell_bright.png")));
		skin.add("boardDark", new Texture(Gdx.files.internal("Textures/boardCell_dark.png")));
		skin.add("pawnBright", new Texture(Gdx.files.internal("Textures/pawn_bright.png")));
		skin.add("pawnDark", new Texture(Gdx.files.internal("Textures/pawn_dark.png")));
	}
	
	private void loadBoard() {
		int width = board.getWidth();
		int height = board.getHeight();
		boardCells = new DrawCell[width][height];
		
		for(int y = 0; y < height; ++y) {
			for(int x = 0; x < width; ++x) {
				boardCells[x][y] = new DrawCell(skin, board.getValue(x, y));
				boardCellContainer.add(boardCells[x][y]).size(65);
			}
			boardCellContainer.row();
		}
	}
}

package com.Horunkan.Draughts.Game.GUI;

import com.Horunkan.Draughts.Draughts;
import com.Horunkan.Draughts.Draughts.ScreenMode;
import com.Horunkan.Draughts.Utilities.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class GameEnd extends Table {
	private final float tableWidth = 300;
	private final float tableHeight = 200;
	private final float buttonWidth = 130;
	private final float buttonHeight = 60;
	private final int titleSize = 25;
	private final int winnerSize = 20;
	private final int buttonSize = 16;
	
	private LabelStyle styleTitle, styleWinner;
	private Label title, winner;
	private TextButton backToMenuButton, newGameButton;
	private Draughts game;
	
	public GameEnd(Draughts game, String winnerName) {
		this.game = game;
		this.setSize(tableWidth, tableHeight);
		setPosition(Draughts.WIDTH/2 - tableWidth/2, Draughts.HEIGHT/2 - tableHeight/2);
		
		styleTitle = new LabelStyle();
		styleWinner = new LabelStyle();
		
		addBackground();
		addTitle();
		addWinner(winnerName);
		addButtons();
		addButtonsListeners();
	}
	
	private void addBackground() {
		//TODO Add texture
		Pixmap pixmap = new Pixmap((int)tableWidth, (int)tableHeight, Format.RGBA8888);
		pixmap.setColor(Color.DARK_GRAY);
		pixmap.fill();
		this.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(pixmap))));
		pixmap.dispose();
	}
	
	private void addTitle() {
		styleTitle.font = Font.get(titleSize);
		styleTitle.fontColor = Color.WHITE;
		
		title = new Label("Game finished", styleTitle);
		this.add(title).expand().colspan(2).row();
	}
	
	private void addWinner(String winnerName) {
		styleWinner.font = Font.get(winnerSize);
		styleWinner.fontColor = Color.WHITE;
		
		winner = new Label(String.format("%s won!", winnerName), styleWinner);
		this.add(winner).expand().colspan(2).row();
	}
	
	private void addButtons() {
		TextButtonStyle style = new TextButtonStyle(TextureLoader.getDrawable("buttonStandard"), TextureLoader.getDrawable("buttonPressed"), TextureLoader.getDrawable("buttonStandard"), Font.get(buttonSize));

		backToMenuButton = new TextButton("Back to menu", style);
		newGameButton = new TextButton("Play again", style);

		this.add(backToMenuButton).size(buttonWidth, buttonHeight).expand();
		this.add(newGameButton).size(buttonWidth, buttonHeight).expand();
	}
	
	private void addButtonsListeners() {
		backToMenuButton.addListener(new ChangeListener() {
			@Override public void changed (ChangeEvent event, Actor actor) {
	           game.setScreen(ScreenMode.MAIN_MENU);
	        }
	    });
		
		newGameButton.addListener(new ChangeListener() {
			@Override public void changed (ChangeEvent event, Actor actor) {
				game.setScreen(ScreenMode.GAME);
	        }
	    });
	}
}

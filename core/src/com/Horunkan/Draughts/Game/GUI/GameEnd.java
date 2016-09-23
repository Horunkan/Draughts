package com.Horunkan.Draughts.Game.GUI;

import com.Horunkan.Draughts.Draughts;
import com.Horunkan.Draughts.Utilities.ButtonStyle;
import com.Horunkan.Draughts.Utilities.FontLoader;
import com.Horunkan.Draughts.Utilities.TextureLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
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
	
	public GameEnd() {
		//this.debug();
		this.setSize(tableWidth, tableHeight);
		setPosition(Draughts.WIDTH/2 - tableWidth/2, Draughts.HEIGHT/2 - tableHeight/2);
		
		styleTitle = new LabelStyle();
		styleWinner = new LabelStyle();
		
		addBackground();
		addTitle();
		addWinner();
		addButtons();
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
		styleTitle.font = FontLoader.getInstance().getFont(titleSize);
		styleTitle.fontColor = Color.WHITE;
		
		title = new Label("Game finished", styleTitle);
		this.add(title).expand().colspan(2).row();
	}
	
	private void addWinner() {
		styleWinner.font = FontLoader.getInstance().getFont(winnerSize);
		styleWinner.fontColor = Color.WHITE;
		
		winner = new Label("[NAME] won!", styleWinner);
		this.add(winner).expand().colspan(2).row();
	}
	
	private void addButtons() {
		FontLoader font = FontLoader.getInstance();
		Skin skin = TextureLoader.getInstace().getSkin();
		TextButtonStyle style = new TextButtonStyle(skin.getDrawable("buttonStandard"), skin.getDrawable("buttonPressed"), skin.getDrawable("buttonStandard"), font.getFont(buttonSize));

		backToMenuButton = new TextButton("Back to menu", style);
		newGameButton = new TextButton("Play again", style);

		this.add(backToMenuButton).size(buttonWidth, buttonHeight).expand();
		this.add(newGameButton).size(buttonWidth, buttonHeight).expand();
	}
}

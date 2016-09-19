package com.Horunkan.Draughts.Game.GUI;

import com.Horunkan.Draughts.Utilities.FontLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class PlayerInfo extends Table {
	private final float tableWidth = 120;
	private final float tableHeight = 100;
	private final int counterFontSize = 15;
	private final int pawnSize = 30;
	
	private int playerFontSize = 25;
	private LabelStyle stylePlayer, styleCounter;
	private Label playerName, countPawns, countKings;
	private Image pawnStandard, pawnKing;
	private Skin skin;
	
	public PlayerInfo(Skin skin, String playerName, String pawnTextureName) {
		//this.debug();
		this.skin = skin;
		this.setSize(tableWidth, tableHeight);
		
		stylePlayer = new LabelStyle();
		
		styleCounter = new LabelStyle();
		styleCounter.font = FontLoader.getInstance().getFont(counterFontSize);
		styleCounter.fontColor = Color.WHITE;
		
		addPlayerName(playerName);
		addPawnStandardCounter(pawnTextureName);
		addPawnKingCounter(pawnTextureName);
	}
	
	public void setValue(int pawns, int kings) {
		countPawns.setText(Integer.toString(pawns));
		countKings.setText(Integer.toString(kings));
	}
	
	private void addPlayerName(String name) {
		stylePlayer.font = FontLoader.getInstance().getFont(playerFontSize);
		stylePlayer.fontColor = Color.WHITE;
		playerName = new Label(name, stylePlayer);
		
		if(playerName.getWidth() <= tableWidth) this.add(playerName).expand().colspan(2).row();
		else {
			playerFontSize -= 3;
			addPlayerName(name);
		}	
	}
	
	private void addPawnStandardCounter(String pawnTextureName) {
		pawnStandard = new Image(skin, pawnTextureName);
		this.add(pawnStandard).expandX().align(Align.center).size(pawnSize);
		
		countPawns = new Label("[VALUE]", styleCounter);
		this.add(countPawns).expandX().align(Align.center).row();
	}
	
	private void addPawnKingCounter(String pawnTextureName) {
		pawnKing = new Image(skin, pawnTextureName/* + "King"*/);
		this.add(pawnKing).expandX().align(Align.center).size(pawnSize);
		
		countKings = new Label("[VALUE]", styleCounter);
		this.add(countKings).expandX().align(Align.center);
	}
}

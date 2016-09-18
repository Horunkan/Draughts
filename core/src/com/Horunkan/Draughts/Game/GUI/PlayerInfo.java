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
	private final int playerFontSize = 25;
	private final int counterFontSize = 15;
	private final int pawnSize = 30;
	
	private LabelStyle stylePlayer, styleCounter;
	private Label playerName, countPawns, countKings;
	private Image pawnStandard, pawnKing;
	
	public PlayerInfo(Skin skin, String playerName) {
		stylePlayer = new LabelStyle();
		stylePlayer.font = FontLoader.getInstance().getFont(playerFontSize);
		stylePlayer.fontColor = Color.WHITE;
		
		styleCounter = new LabelStyle();
		styleCounter.font = FontLoader.getInstance().getFont(counterFontSize);
		styleCounter.fontColor = Color.WHITE;
		
		this.playerName = new Label(playerName, stylePlayer);
		this.add(this.playerName).colspan(2).row();
		
		pawnStandard = new Image(skin, "pawnDark");
		this.add(pawnStandard).align(Align.left).size(pawnSize);

		countPawns = new Label("[VALUE]", styleCounter);
		this.add(countPawns).align(Align.right).row();
		
		pawnKing = new Image(skin, "pawnDark");
		this.add(pawnKing).align(Align.left).size(pawnSize);
		
		countKings = new Label("[VALUE]", styleCounter);
		this.add(countKings).align(Align.right);
	}
}

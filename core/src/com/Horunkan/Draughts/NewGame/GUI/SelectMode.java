package com.Horunkan.Draughts.NewGame.GUI;

import com.Horunkan.Draughts.Utilities.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class SelectMode {
	private final int checkBoxFontSize = 20;
	private final int checkBoxSize = 30;
	
	private CheckBoxStyle checkBoxStyle;
	private CheckBox playerVsPlayer, playerVsCPU;
	private ButtonGroup<CheckBox> checkBoxGroup;
	private Pixmap checkBoxOn, checkBoxOff;
	
	public SelectMode() {
		createPixmaps();
		createCheckBoxStyle();
		createCheckBoxes();
		createCheckBoxGroup();
		disposePixmaps();
		
		playerVsCPU.setDisabled(true);
	}
	
	public void addToTable(Table tab) {
		tab.add(playerVsPlayer).pad(5f);
		tab.add(playerVsCPU).pad(5f);
	}
		
	//TODO Add textures
	private void createPixmaps() {
		checkBoxOn = new Pixmap(checkBoxSize, checkBoxSize, Format.RGBA8888);
		checkBoxOn.setColor(Color.DARK_GRAY);
		checkBoxOn.fill();
		
		checkBoxOff = new Pixmap(checkBoxSize, checkBoxSize, Format.RGBA8888);
		checkBoxOff.setColor(Color.LIGHT_GRAY);
		checkBoxOff.fill();
	}
	
	private void createCheckBoxStyle() {
		checkBoxStyle = new CheckBoxStyle();
		checkBoxStyle.font = FontLoader.getInstance().getFont(checkBoxFontSize);
		checkBoxStyle.fontColor = new Color(Color.WHITE);
		checkBoxStyle.disabledFontColor = new Color(Color.DARK_GRAY);
		checkBoxStyle.checkboxOn = new TextureRegionDrawable(new TextureRegion(new Texture(checkBoxOn)));
		checkBoxStyle.checkboxOff = new TextureRegionDrawable(new TextureRegion(new Texture(checkBoxOff)));
	}
	
	private void createCheckBoxes() {
		playerVsPlayer = new CheckBox(" Player vs Player", checkBoxStyle);
		playerVsCPU = new CheckBox(" Player vs CPU", checkBoxStyle);
		playerVsPlayer.setChecked(true);
	}
	
	private void createCheckBoxGroup() {
		checkBoxGroup = new ButtonGroup<CheckBox>(playerVsPlayer, playerVsCPU);
		checkBoxGroup.setMaxCheckCount(1);
		checkBoxGroup.setMinCheckCount(1);
	}
	
	private void disposePixmaps() {
		checkBoxOn.dispose();
		checkBoxOff.dispose();
	}
}

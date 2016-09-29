package com.Horunkan.Draughts.NewGame.GUI;

import com.Horunkan.Draughts.Utilities.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class SelectMode {
	private final int checkBoxFontSize = 20;
	private final int checkBoxSize = 30;
	
	private CheckBoxStyle checkBoxStyle;
	private CheckBox playerVsPlayer, playerVsCPU;
	private ButtonGroup<CheckBox> checkBoxGroup;
	
	public SelectMode() {
		createCheckBoxStyle();
		createCheckBoxes();
		createCheckBoxGroup();

		playerVsCPU.setDisabled(true);
		playerVsCPU.getImage().setColor(Color.DARK_GRAY);
	}
	
	public void addToTable(Table tab) {
		tab.add(playerVsPlayer).pad(5f);
		tab.add(playerVsCPU).pad(5f);
	}
	
	private void createCheckBoxStyle() {
		checkBoxStyle = new CheckBoxStyle();
		checkBoxStyle.font = Font.get(checkBoxFontSize);
		checkBoxStyle.fontColor = new Color(Color.WHITE);
		checkBoxStyle.disabledFontColor = new Color(Color.DARK_GRAY);
		checkBoxStyle.checkboxOn = TextureLoader.getDrawable("checkboxOn");
		checkBoxStyle.checkboxOff = TextureLoader.getDrawable("checkboxOff");
	}
	
	private void createCheckBoxes() {
		playerVsPlayer = new CheckBox(" Player vs Player", checkBoxStyle);
		playerVsCPU = new CheckBox(" Player vs CPU", checkBoxStyle);
		playerVsPlayer.setChecked(true);
		playerVsPlayer.getImageCell().size(checkBoxSize);
		playerVsCPU.getImageCell().size(checkBoxSize);
	}
	
	private void createCheckBoxGroup() {
		checkBoxGroup = new ButtonGroup<CheckBox>(playerVsPlayer, playerVsCPU);
		checkBoxGroup.setMaxCheckCount(1);
		checkBoxGroup.setMinCheckCount(1);
	}
}

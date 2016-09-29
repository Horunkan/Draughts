package com.Horunkan.Draughts.NewGame.GUI;

import com.Horunkan.Draughts.Utilities.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class SelectNames {
	private final int fieldFontSize = 20;
	
	private LabelStyle nameStyle;
	private Label playerBright, playerDark;
	private TextField playerBrightValue, playerDarkValue;
	private TextFieldStyle fieldStyle;
	private Table contaier;
	
	public SelectNames() {
		createFieldsNames();
		createFieldStyle();
		createFields();
		createContainer();
	}
	
	public void addToTable(Table tab) {
		tab.add(contaier).top().pad(5);
	}
	
	public String[] getNames() {
		String buffer[] = new String[2];
		buffer[0] = playerBrightValue.getText();
		buffer[1] = playerDarkValue.getText();
		return buffer;
	}
		
	private void createFieldsNames() {
		nameStyle = new LabelStyle();
		nameStyle.font = Font.get(fieldFontSize);
		nameStyle.fontColor = Color.WHITE;
		
		playerBright = new Label("Bright pawns: ", nameStyle);
		playerDark = new Label("Dark pawns: ", nameStyle);
	}
	
	private void createFieldStyle() {
		fieldStyle = new TextFieldStyle();
		fieldStyle.font = Font.get(fieldFontSize);
		fieldStyle.fontColor = Color.WHITE;
		fieldStyle.cursor = TextureLoader.getDrawable(1, 15, Color.WHITE);
		fieldStyle.background = TextureLoader.getDrawable(1, 15, Color.DARK_GRAY);
		fieldStyle.selection = TextureLoader.getDrawable(1, 15, Color.LIGHT_GRAY);
		fieldStyle.background.setLeftWidth(5);
	}
	
	private void createFields() {
		playerBrightValue = new TextField("Player A", fieldStyle);
		playerDarkValue = new TextField("Player B", fieldStyle);
		playerBrightValue.setMaxLength(15);
		playerDarkValue.setMaxLength(15);
	}
	
	private void createContainer() {
		contaier = new Table();
		contaier.add(playerBright).left().space(15).width(140);
		contaier.add(playerBrightValue).right().space(15).row();
		contaier.add(playerDark).left().space(15).width(140);
		contaier.add(playerDarkValue).right();
	}
}

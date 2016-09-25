package com.Horunkan.Draughts.NewGame.GUI;

import com.Horunkan.Draughts.Utilities.FontLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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
		
	private void createFieldsNames() {
		nameStyle = new LabelStyle();
		nameStyle.font = FontLoader.getInstance().getFont(fieldFontSize);
		nameStyle.fontColor = Color.WHITE;
		
		playerBright = new Label("Bright pawns: ", nameStyle);
		playerDark = new Label("Dark pawns: ", nameStyle);
	}
	
	private void createFieldStyle() {
		Pixmap cursor = new Pixmap(1, 15, Format.RGBA8888);
		cursor.setColor(Color.WHITE);
		cursor.fill();
		
		Pixmap background = new Pixmap(1, 15, Format.RGBA8888);
		background.setColor(Color.DARK_GRAY);
		background.fill();
		
		Pixmap selection = new Pixmap(1, 15, Format.RGBA8888);
		selection.setColor(Color.LIGHT_GRAY);
		selection.fill();
		
		fieldStyle = new TextFieldStyle();
		fieldStyle.font = FontLoader.getInstance().getFont(fieldFontSize);
		fieldStyle.fontColor = Color.WHITE;
		fieldStyle.cursor = new TextureRegionDrawable(new TextureRegion(new Texture(cursor)));
		fieldStyle.background = new TextureRegionDrawable(new TextureRegion(new Texture(background)));
		fieldStyle.selection = new TextureRegionDrawable(new TextureRegion(new Texture(selection)));
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

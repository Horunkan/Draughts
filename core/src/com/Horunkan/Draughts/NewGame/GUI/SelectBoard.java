package com.Horunkan.Draughts.NewGame.GUI;

import com.Horunkan.Draughts.Utilities.FontLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class SelectBoard {
	private final int listFontSize = 15;

	private ListStyle listStyle;
	private List<String> boardList;
	private ScrollPaneStyle scrollStyle;
	private ScrollPane boardsScroller;
	
	public SelectBoard() {
		createListStyle();
		loadList();
		createScrollPaneStyle();
		createScrollPane();
	}
	
	public void addToTable(Table tab) {
		tab.add(boardsScroller).width(250).height(250).pad(5);
	}
		
	private void createListStyle() {
		Pixmap selectionPixmap = new Pixmap(100, 100, Format.RGBA8888);
		selectionPixmap.setColor(Color.DARK_GRAY);
		selectionPixmap.fill();
		
		listStyle = new ListStyle();
		listStyle.font = FontLoader.getInstance().getFont(listFontSize);
		listStyle.fontColorUnselected = Color.WHITE;
		listStyle.fontColorSelected = Color.WHITE;
		listStyle.selection = new TextureRegionDrawable(new TextureRegion(new Texture(selectionPixmap)));
	}
	
	private void loadList() {
		boardList = new List<String>(listStyle);
		FileHandle directory = Gdx.files.internal("Boards");
		String[] buffer = new String[directory.list().length];

		for(int i = 0; i < directory.list().length; ++i) buffer[i] = directory.list()[i].name();
		boardList.setItems(buffer);
	}
	
	private void createScrollPaneStyle() {
		Pixmap scrollKnobPixmap = new Pixmap(15, 15, Format.RGBA8888);
		scrollKnobPixmap.setColor(Color.DARK_GRAY);
		scrollKnobPixmap.fill();
		
		Pixmap scrollPixmap = new Pixmap(15, 15, Format.RGBA8888);
		scrollPixmap.setColor(Color.LIGHT_GRAY);
		scrollPixmap.fill();
		
		scrollStyle = new ScrollPaneStyle();
		scrollStyle.vScrollKnob = new TextureRegionDrawable(new TextureRegion(new Texture(scrollKnobPixmap)));
		scrollStyle.vScroll = new TextureRegionDrawable(new TextureRegion(new Texture(scrollPixmap)));
	}
	
	private void createScrollPane() {
		boardsScroller = new ScrollPane(boardList, scrollStyle);
		boardsScroller.setOverscroll(false, false);
		boardsScroller.setFadeScrollBars(false);
	}
}

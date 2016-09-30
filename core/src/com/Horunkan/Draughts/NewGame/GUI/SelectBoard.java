package com.Horunkan.Draughts.NewGame.GUI;

import java.util.ArrayList;

import com.Horunkan.Draughts.Utilities.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;

public class SelectBoard {
	private final int listFontSize = 15;
	private final float listWidth = 250;
	private final float listHeight = 250;

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
		tab.add(boardsScroller).width(listWidth).height(listHeight).pad(5);
	}
	
	public String getSelectedBoard() { return boardList.getSelected() + ".txt"; }
		
	private void createListStyle() {		
		listStyle = new ListStyle();
		listStyle.font = Font.get(listFontSize);
		listStyle.fontColorUnselected = Color.WHITE;
		listStyle.fontColorSelected = Color.WHITE;
		listStyle.selection = TextureLoader.getDrawable(100, 100, Color.DARK_GRAY);
	}
	
	//TODO BUG: Boards don't load after build to jar file
	private void loadList() {
		boardList = new List<String>(listStyle);
		FileHandle directory = Gdx.files.internal("Boards");
		ArrayList<String> buffer = new ArrayList<String>();
		
		for(FileHandle file : directory.list()) {
			String fileName[] = file.name().split(".txt");
			buffer.add(fileName[0]);
		}
		boardList.setItems(buffer.toArray(new String[0]));
	}
	
	private void createScrollPaneStyle() {
		scrollStyle = new ScrollPaneStyle();
		scrollStyle.vScrollKnob = TextureLoader.getDrawable(15, 15, Color.DARK_GRAY);
		scrollStyle.vScroll = TextureLoader.getDrawable(15, 15, Color.LIGHT_GRAY);
	}
	
	private void createScrollPane() {
		boardsScroller = new ScrollPane(boardList, scrollStyle);
		boardsScroller.setOverscroll(false, false);
		boardsScroller.setFadeScrollBars(false);
	}
}

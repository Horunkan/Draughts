package com.Horunkan.Draughts;

public class BoardPosition {
	public int x = 0;
	public int y = 0;
	
	public BoardPosition(int x, int y) {
		setPosition(x, y);
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

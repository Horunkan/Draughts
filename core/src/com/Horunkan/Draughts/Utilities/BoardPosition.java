package com.Horunkan.Draughts.Utilities;

public class BoardPosition {
	public int x , y;
	
	public BoardPosition(int x, int y) {
		setPosition(x, y);
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public static BoardPosition getDistance(BoardPosition posA, BoardPosition posB) {
		int distX = Math.abs(posA.x - posB.x);
		int distY = Math.abs(posA.y - posB.y);
		
		return new BoardPosition(distX, distY);
	}
	
	public String toString() { return "[" + x + "," + y + "]"; }
}

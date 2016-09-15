package com.Horunkan.Draughts.Game.Logic;

import com.Horunkan.Draughts.Game.GUI.DrawCell;
import com.Horunkan.Draughts.Game.GUI.DrawPawn;

public class BoardDebug {
	protected boolean debug = false;
	
	protected DrawCell[][] boardCells;
	protected DrawPawn[] pawnsBright, pawnsDark;
	
	public void debug(DrawCell[][] cells, DrawPawn[] pawnsBright, DrawPawn[] pawnsDark) {
		debug = true;
		boardCells = cells;
		this.pawnsBright = pawnsBright;
		this.pawnsDark = pawnsDark;
	}
}

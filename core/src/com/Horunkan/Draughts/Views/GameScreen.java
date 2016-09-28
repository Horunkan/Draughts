package com.Horunkan.Draughts.Views;

import java.util.ArrayList;
import com.Horunkan.Draughts.Draughts;
import com.Horunkan.Draughts.Game.GUI.*;
import com.Horunkan.Draughts.Game.GUI.DrawPawn.PawnType;
import com.Horunkan.Draughts.Game.Logic.Board;
import com.Horunkan.Draughts.Game.Logic.ActivePawn;
import com.Horunkan.Draughts.Game.Logic.Player;
import com.Horunkan.Draughts.Game.Logic.Player.Players;
import com.Horunkan.Draughts.Utilities.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class GameScreen extends AbstractScreen {
	private Table boardCellContainer;
	private Board board;
	private DrawCell[][] boardCells;
	ArrayList<DrawPawn> pawns;
	private PlayerInfo playerDark, playerBright;
	private GameEnd end;
	private int boardCellSize = 65;
	private Player player;
	
	public GameScreen(Draughts game) { 
		super(game); 
		board = new Board();
		player = new Player(this);
		ActivePawn.setGameScreen(this);
		ActivePawn.setBoard(board);
	}
	
	@Override
	public void render(float delta) {
		clearScreen();
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) game.setScreen(Draughts.ScreenMode.MAIN_MENU);
		
		stage.draw();
	}
		
	public void newGame(String[] playerNames, String boardName) {
		System.out.println("\nNew Game\n");
		for(Actor act : stage.getActors()) act.remove();
		if(pawns != null) for(DrawPawn pw : pawns) pw.remove();
		
		boardCellContainer = new Table();
		boardCellContainer.setFillParent(true);
		
		board.loadFromFile(boardName);
		countBoardCellSize();
		loadBoard();
		
		pawns = new ArrayList<DrawPawn>();		
		stage.addActor(boardCellContainer);
		boardCellContainer.validate();
		
		loadPawnsGroups();
		loadPlayerInfo(playerNames);
		countPawns();
		player.set(Players.BRIGHT);
		updateActivePlayer();
	}
	
	public DrawCell getCell(BoardPosition pos) {
		return boardCells[pos.x][pos.y];
	}
	
	public void removePawn(int x, int y) {
		for(int i = 0; i < pawns.size(); ++i) {
			if(pawns.get(i).getBoardPosition().isEqual(x, y)) {
				pawns.get(i).remove();
				pawns.remove(i);
				break;
			}
		}
		countPawns();
		checkEndGame();
	}
	
	public void countPawns() {
		int pawnsBright = 0, pawnsBrightKings = 0, pawnsDark = 0, pawnsDarkKings = 0;
		
		for(DrawPawn pw : pawns) {
			if(pw.getPlayer() == Players.BRIGHT) {
				if(pw.getType() == PawnType.STANDARD) ++pawnsBright;
				else ++pawnsBrightKings;
			}
			else {
				if(pw.getType() == PawnType.STANDARD) ++pawnsDark;
				else ++pawnsDarkKings;
			}
		}
		
		playerBright.setValue(pawnsBright, pawnsBrightKings);
		playerDark.setValue(pawnsDark, pawnsDarkKings);
	}
	
	private void checkEndGame() {
		if(board.countPawns(2) == 0 && board.countPawns(4) == 0) {
			end = new GameEnd(game, playerDark.getName());
			stage.addActor(end);
		}
		else if(board.countPawns(3) == 0 && board.countPawns(5) == 0) {
			end = new GameEnd(game, playerBright.getName());
			stage.addActor(end);
		}
	}
	
	public void updateActivePlayer() {
		playerBright.setColor(Color.WHITE);
		playerDark.setColor(Color.WHITE);
		
		if(player.getActive() == Players.BRIGHT) playerBright.setColor(Color.GREEN);
		else playerDark.setColor(Color.GREEN);
	}
	
	private void countBoardCellSize() {
		boardCellSize = 65;
		while(board.getWidth() * boardCellSize >= Draughts.WIDTH - 10) { boardCellSize -= 1; }
		while(board.getHeight() * boardCellSize >= Draughts.HEIGHT - 60) { boardCellSize -= 1; }
	}
	
	public int getBoardCellSize() { return boardCellSize; }
	
	private void loadBoard() {
		int width = board.getWidth();
		int height = board.getHeight();
		boardCells = new DrawCell[width][height];
		
		for(int y = 0; y < height; ++y) {
			for(int x = 0; x < width; ++x) {
				boardCells[x][y] = new DrawCell(player, board.getValue(x, y), x, y);
				boardCellContainer.add(boardCells[x][y]).size(boardCellSize);
			}
			boardCellContainer.row();
		}
	}
	
	private void loadPawnsGroups() {
		for(int y = 0; y < board.getHeight(); ++y) {
			for(int x = 0; x < board.getWidth(); ++x) {
				int boardVal = board.getValue(x, y);
				
				if(boardVal != 0 && boardVal != 1) {
					DrawPawn buffer = new DrawPawn(player, boardVal, x, y);
					buffer.setPosition(boardCells[x][y].getPosition().x, boardCells[x][y].getPosition().y);
					buffer.setSize(boardCellSize, boardCellSize);
					pawns.add(buffer);
					stage.addActor(buffer);
				}
			}
		}
	}

	private void loadPlayerInfo(String[] playerNames) {
		if(playerBright != null) playerBright.remove();
		playerBright = new PlayerInfo(playerNames[0], "pawnBright");
		playerBright.setPosition(670, 460);
		stage.addActor(playerBright);
		playerBright.validate();

		if(playerDark != null) playerDark.remove();
		playerDark = new PlayerInfo(playerNames[1], "pawnDark");
		playerDark.setPosition(10, 40);
		stage.addActor(playerDark);
		playerDark.validate();
	}
}

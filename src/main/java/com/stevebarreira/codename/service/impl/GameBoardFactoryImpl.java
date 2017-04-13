package com.stevebarreira.codename.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stevebarreira.codename.model.GameBoard;
import com.stevebarreira.codename.model.GameRow;
import com.stevebarreira.codename.model.GameTile;
import com.stevebarreira.codename.service.GameBoardFactory;
import com.stevebarreira.codename.service.WordListService;

@Service
public class GameBoardFactoryImpl implements GameBoardFactory {

	@Autowired
	WordListService wordListService;
	
	@Override
	public GameBoard createRandomGameBoard() {
		GameBoard gameBoard = new GameBoard();
		gameBoard.setWordList(wordListService.getRandomWordList());
		gameBoard.setGameRows(getRows());
		gameBoard.assignTeams();
		return gameBoard;
	}
	
	private GameRow createRow(){
		GameRow gameRow = new GameRow();
		List<GameTile> tiles = new ArrayList<GameTile>();
		for(int i = 0; i < 5; i ++){
			tiles.add(new GameTile());
		}
		gameRow.setRowTiles(tiles);
		return gameRow;
	}
	
	private List<GameRow> getRows(){
		List<GameRow> gameRows = new ArrayList<GameRow>();
		for(int i = 0; i < 5; i ++){
			gameRows.add(createRow());
		}
		return gameRows;
	}

}

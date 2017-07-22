package com.stevebarreira.codename.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.stevebarreira.codename.model.GameBoards;
import com.stevebarreira.codename.model.GameRow;
import com.stevebarreira.codename.model.GameTile;
import com.stevebarreira.codename.model.WordList;
import com.stevebarreira.codename.repository.GameBoardRepository;
import com.stevebarreira.codename.service.GameBoardService;
import com.stevebarreira.codename.service.WordListService;

public class GameBoardServiceImpl implements GameBoardService {

	@Autowired
	GameBoardRepository gameBoardRepository;
	
	@Autowired
	WordListService wordListService;
	
	@Override
	public GameBoards createRandomGameBoard() {
		GameBoards gameBoard = new GameBoards();
		WordList wordList = wordListService.getRandomWordList();
		if (wordList != null) {
			gameBoard.setWordList(wordList);
			gameBoard.setGameRows(createGameRows());
			gameBoard.assignTeams();
			gameBoard = gameBoardRepository.save(gameBoard);
		}
		return gameBoard;
	}

	@Override
	public List<GameBoards> getAllGameBoards() {
		return gameBoardRepository.findAll();
	}

	@Override
	public GameBoards getGameBoardById(String id) {
		return gameBoardRepository.findOne(id);
	}

	@Override
	public GameBoards updateGameBoard(GameBoards gameBoard) {
		return gameBoardRepository.save(gameBoard);
	}

	private List<GameRow> createGameRows() {
		List<GameRow> gameRows = new ArrayList<GameRow>();
		for (int i = 0; i < 5; i++) {
			gameRows.add(createGameRow());
		}
		return gameRows;
	}
	
	private GameRow createGameRow() {
		GameRow gameRow = new GameRow();
		List<GameTile> tiles = new ArrayList<GameTile>();
		for (int i = 0; i < 5; i++) {
			tiles.add(new GameTile());
		}
		gameRow.setRowTiles(tiles);
		return gameRow;
	}
}

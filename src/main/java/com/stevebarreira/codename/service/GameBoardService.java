package com.stevebarreira.codename.service;

import com.stevebarreira.codename.model.GameBoards;

import java.util.List;

public interface GameBoardService {
	
	GameBoards createRandomGameBoard() throws RuntimeException;
	
	List<GameBoards> getAllGameBoards();
	
	GameBoards getGameBoardById(String id);
	
	GameBoards updateGameBoard(GameBoards gameBoard);

}

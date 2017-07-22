package com.stevebarreira.codename.service;

import java.util.List;

import com.stevebarreira.codename.model.GameBoards;

public interface GameBoardService {
	
	public GameBoards createRandomGameBoard();
	
	public List<GameBoards> getAllGameBoards();
	
	public GameBoards getGameBoardById(String id);
	
	public GameBoards updateGameBoard(GameBoards gameBoard);

}

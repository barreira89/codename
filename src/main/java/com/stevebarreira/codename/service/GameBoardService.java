package com.stevebarreira.codename.service;

import com.stevebarreira.codename.model.GameBoards;

import java.util.List;

public interface GameBoardService {
	
	public GameBoards createRandomGameBoard();
	
	public List<GameBoards> getAllGameBoards();
	
	public GameBoards getGameBoardById(String id);
	
	public GameBoards updateGameBoard(GameBoards gameBoard);

}

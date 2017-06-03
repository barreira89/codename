package com.stevebarreira.codename.service;

import com.stevebarreira.codename.model.Games;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.stevebarreira.codename.model.GameBoards;
import com.stevebarreira.codename.model.GameClue;

//Breakout into gameboard service
public interface GameService {
	
	public GameBoards createRandomGameBoard();
	
	public Games createNewGame();
	
	public Games getGameById(String id);
	
	public Games newRoundForGame(String id);
	
	public Games addClueToGameRound(String id, Integer roundNumber, GameClue gameClue);
	
	public List<GameClue> getCluesByGameAndRound(String id, Integer roundNumber);
	
	public Page<Games> getAllGames(Pageable pageable);
	
	public Games updateGame(Games game);
	
	public GameBoards updateGameBoard(GameBoards gameBoard);
	
	public List<GameBoards> getAllGameBoards();
	
	public GameBoards getGameBoardById(String id);

}

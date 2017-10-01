package com.stevebarreira.codename.service;

import com.stevebarreira.codename.model.GameClue;
import com.stevebarreira.codename.model.Games;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

//Breakout into gameboard service
public interface GameService {
	
	public Games createNewGame();
	
	public Games getGameById(String id);
	
	public Games newRoundForGame(String id);
	
	public Games addClueToGameRound(String id, Integer roundNumber, GameClue gameClue);
	
	public List<GameClue> getCluesByGameAndRound(String id, Integer roundNumber);
	
	public Page<Games> getAllGames(Pageable pageable);
	
	public Games updateGame(Games game);
	
}

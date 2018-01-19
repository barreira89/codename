package com.stevebarreira.codename.service;

import com.stevebarreira.codename.model.GameClue;
import com.stevebarreira.codename.model.Games;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GameService {
	
	Games createNewGame();
	
	Games getGameById(String id);
	
	Games newRoundForGame(String id);
	
	Games addClueToGameRound(String id, Integer roundNumber, GameClue gameClue);
	
	List<GameClue> getCluesByGameAndRound(String id, Integer roundNumber);
	
	Page<Games> getAllGames(Pageable pageable);
	
	Games updateGame(Games game);

	void deleteGameById(String id);
}

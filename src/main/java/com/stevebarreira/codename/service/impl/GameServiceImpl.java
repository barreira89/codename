package com.stevebarreira.codename.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.stevebarreira.codename.model.Games;
import com.stevebarreira.codename.model.WordList;
import com.stevebarreira.codename.repository.GameBoardRepository;
import com.stevebarreira.codename.repository.GamesRepository;
import com.stevebarreira.codename.model.GameBoards;
import com.stevebarreira.codename.model.GameClue;
import com.stevebarreira.codename.model.GameRound;
import com.stevebarreira.codename.model.GameRow;
import com.stevebarreira.codename.model.GameTile;
import com.stevebarreira.codename.service.GameBoardService;
import com.stevebarreira.codename.service.GameService;
import com.stevebarreira.codename.service.WordListService;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	WordListService wordListService;

	@Autowired
	GamesRepository gameRepository;

	@Autowired
	GameBoardRepository gameBoardRepository;
	
	@Autowired
	GameBoardService gameBoardService;

	private GameRound createNewGameRound(int roundNumber) {
		GameRound gameRound = new GameRound();
		gameRound.setGameBoard(gameBoardService.createRandomGameBoard());
		gameRound.setRoundNumber(roundNumber);
		return gameRound;
	}

	@Override
	public Games createNewGame() {
		Games game = new Games();
		List<GameRound> rounds = new ArrayList<GameRound>();
		GameRound gameRound = createNewGameRound(1);
		rounds.add(gameRound);
		game.setRounds(rounds);
		return gameRepository.save(game);
	}

	@Override
	public Games getGameById(String id) {
		return gameRepository.findOne(id);
	}

	@Override
	public Games newRoundForGame(String id) {
		Games currentGame = gameRepository.findOne(id);
		int highestRound = currentGame.getRounds().stream()
				.max((p1, p2) -> Integer.compare(p1.getRoundNumber(), p2.getRoundNumber())).get().getRoundNumber();
		highestRound++;
		currentGame.addGameRound(createNewGameRound(highestRound));
		gameRepository.save(currentGame);
		return currentGame;
	}

	@Override
	public Page<Games> getAllGames(Pageable pageable) {
		return gameRepository.findAll(pageable);
	}

	@Override
	public Games updateGame(Games game) {
		return gameRepository.save(game);
	}

	@Override
	public Games addClueToGameRound(String id, Integer roundNumber, GameClue gameClue) {		
		Optional<GameClue> gameClueToAdd = Optional.ofNullable(gameClue);
				
		Games game = gameRepository.findOne(id);
		
		// Find Round by Number
		Optional<GameRound> gameRound = game.getRounds().stream()
				.filter(gr -> gr.getRoundNumber() == roundNumber)
				.findFirst();

		// Add Clue
		gameRound.ifPresent(gr2 -> {
			gameClueToAdd.ifPresent(gc -> gr2.addClue(gc));
		});

		// Save Game
		return gameRepository.save(game);

	}

	@Override
	public List<GameClue> getCluesByGameAndRound(String id, Integer roundNumber) {
		List<GameClue> gameClueList = new ArrayList<GameClue>();
		
		Optional.ofNullable(gameRepository.findOne(id))
			.ifPresent(resultingGame -> {
				Optional.ofNullable(resultingGame.getRounds())
					.ifPresent(foundGameRounds -> {
						foundGameRounds.stream().filter(gr -> gr.getRoundNumber() == roundNumber).findFirst()
						.ifPresent(matchingGameRound -> {
							Optional.ofNullable(matchingGameRound.getGameClues())
								.ifPresent(matchingGameClueList -> {
									gameClueList.addAll(matchingGameClueList);
							});
						});
					});
			});


		return gameClueList;
	}

}

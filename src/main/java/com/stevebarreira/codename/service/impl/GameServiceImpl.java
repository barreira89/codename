package com.stevebarreira.codename.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	private GameRow createGameRow() {
		GameRow gameRow = new GameRow();
		List<GameTile> tiles = new ArrayList<GameTile>();
		for (int i = 0; i < 5; i++) {
			tiles.add(new GameTile());
		}
		gameRow.setRowTiles(tiles);
		return gameRow;
	}

	private List<GameRow> createGameRows() {
		List<GameRow> gameRows = new ArrayList<GameRow>();
		for (int i = 0; i < 5; i++) {
			gameRows.add(createGameRow());
		}
		return gameRows;
	}

	private GameRound createNewGameRound(int roundNumber) {
		GameRound gameRound = new GameRound();
		gameRound.setGameBoard(createRandomGameBoard());
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
	public GameBoards updateGameBoard(GameBoards gameBoard) {
		return gameBoardRepository.save(gameBoard);
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
	public Games addClueToGameRound(String id, Integer roundNumber, GameClue gameClue) {
		Games game = gameRepository.findOne(id);

		// Find Round by Number
		Optional<GameRound> gameRound = game.getRounds().stream().filter(gr -> gr.getRoundNumber() == roundNumber)
				.findFirst();

		// Add Clue
		gameRound.ifPresent(gr2 -> gr2.addClue(gameClue));

		// Save Game
		return gameRepository.save(game);

	}

	@Override
	public List<GameClue> getCluesByGameAndRound(String id, Integer roundNumber) {
		Games game = gameRepository.findOne(id);
		List<GameClue> gameClueList = new ArrayList<GameClue>();

		Optional<GameRound> gameRound = Optional.empty();
		
		if(game != null && game.getRounds()!= null) {
			gameRound = game.getRounds().stream().filter(gr -> gr.getRoundNumber() == roundNumber).findFirst();
		}

		gameRound.ifPresent(gr -> {
			
			gameClueList.addAll(gr.getGameClues());
		});

		return gameClueList;
	}

}

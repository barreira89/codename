package com.stevebarreira.codename.service.impl;

import com.stevebarreira.codename.exception.EntityNotFoundException;
import com.stevebarreira.codename.model.GameClue;
import com.stevebarreira.codename.model.GameRound;
import com.stevebarreira.codename.model.Games;
import com.stevebarreira.codename.model.dto.GamesDto;
import com.stevebarreira.codename.model.transformer.impl.GamesTransformer;
import com.stevebarreira.codename.model.validator.Validator;
import com.stevebarreira.codename.repository.GamesRepository;
import com.stevebarreira.codename.service.GameBoardService;
import com.stevebarreira.codename.service.GameService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(GameServiceImpl.class);

    @Autowired
    private GamesRepository gameRepository;

    @Autowired
    private GameBoardService gameBoardService;

    @Autowired
    private GamesTransformer gamesTransformer;

    @Autowired
    private Validator<GameClue> clueValidator;

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
        return gameRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Game Not Found"));
    }

    @Override
    public GamesDto getGameDtoById(String id) {
        return gameRepository
                .findById(id)
                .map(gamesTransformer::transform)
                .orElseThrow(() -> new EntityNotFoundException("Game Not Found"));
    }

    @Override
    public Games newRoundForGame(String id) {
        Games currentGame = gameRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Game Not Found"));
        int highestRound = getHighestRound(currentGame);
        highestRound++;
        currentGame.addGameRound(createNewGameRound(highestRound));
        gameRepository.save(currentGame);
        return currentGame;
    }

    private int getHighestRound(Games game) {
        return game.getRounds().stream()
                .mapToInt(GameRound::getRoundNumber)
                .max()
                .orElse(0);
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
        if (gameClue == null || gameClue.getClue() == null) {
            throw new IllegalArgumentException("Game Clue Is Required");
        }
        if (!clueValidator.isValid(gameClue)) {
            throw new IllegalArgumentException("Game Clue Invalid");
        }

        Optional<Games> game = gameRepository.findById(id);
        game
                .map(g -> g.getRoundByRoundNumber(roundNumber))
                .ifPresent(gr -> gr.addClue(gameClue));
        //Find the round that matches the number
        game.ifPresent(gameRepository::save);

        return game.orElse(null);
    }

    @Override
    public List<GameClue> getCluesByGameAndRound(String id, Integer roundNumber) {
        return gameRepository
                .findById(id)
                .map(Games::getRounds)
                .flatMap(gameRounds -> getGameCluesFromRoundByRoundNumber(gameRounds, roundNumber))
                .orElseGet(Collections::emptyList);
    }

    private Optional<List<GameClue>> getGameCluesFromRoundByRoundNumber(List<GameRound> rounds, Integer roundNumber) {
        return rounds
                .stream()
                .filter(gameRound -> gameRound.getRoundNumber() == roundNumber)
                .map(GameRound::getGameClues)
                .filter(Objects::nonNull)
                .findFirst();
    }

    //TODO add Unit Tests
    public void deleteGameById(String id) {
        gameRepository.deleteById(id);
    }

}

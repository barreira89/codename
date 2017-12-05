package com.stevebarreira.codename.service.impl;

import com.stevebarreira.codename.model.GameClue;
import com.stevebarreira.codename.model.GameRound;
import com.stevebarreira.codename.model.Games;
import com.stevebarreira.codename.repository.GamesRepository;
import com.stevebarreira.codename.service.GameBoardService;
import com.stevebarreira.codename.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    GamesRepository gameRepository;

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
        if(gameClue == null){
            throw new IllegalArgumentException("Game Clue Is Required");
        }

        Optional<Games> game = Optional.ofNullable(gameRepository.findOne(id));
        game
                .map(g -> g.getRoundByRoundNumber(roundNumber))
                .ifPresent(gr -> gr.addClue(gameClue));
        //Find the round that matches the number
        game.ifPresent(gameRepository::save);

        return game.orElse(null);
    }

    @Override
    public List<GameClue> getCluesByGameAndRound(String id, Integer roundNumber) {
        return Optional.ofNullable(gameRepository.findOne(id))
                .map(Games::getRounds)
                .flatMap(gameRounds -> gameRounds.stream()
                        .filter(gr -> gr.getRoundNumber() == roundNumber)
                        .map(GameRound::getGameClues)
                        .filter(Objects::nonNull)
                        .findFirst())
                .orElseGet(Collections::emptyList);
    }

}

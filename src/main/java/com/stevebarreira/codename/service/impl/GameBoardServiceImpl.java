package com.stevebarreira.codename.service.impl;

import com.stevebarreira.codename.model.GameBoards;
import com.stevebarreira.codename.model.GameRow;
import com.stevebarreira.codename.model.GameTile;
import com.stevebarreira.codename.model.WordList;
import com.stevebarreira.codename.repository.GameBoardRepository;
import com.stevebarreira.codename.service.GameBoardService;
import com.stevebarreira.codename.service.WordListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class GameBoardServiceImpl implements GameBoardService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameBoardServiceImpl.class);

    @Autowired
    private GameBoardRepository gameBoardRepository;

    @Autowired
    private WordListService wordListService;

    @Override
    public GameBoards createRandomGameBoard() throws RuntimeException {
        GameBoards gameBoard;
        WordList wordList = wordListService.getRandomWordList();
        if(wordList != null){
            gameBoard = setupGameBoard(wordList);
        } else {
            throw new RuntimeException("No WordList Available, Default Failure");
        }
        return gameBoardRepository.save(gameBoard);
    }

    private GameBoards setupGameBoard(WordList wordList){
        GameBoards gameBoard = new GameBoards();
        gameBoard.setWordList(wordList);
        gameBoard.setGameRows(createGameRows());
        gameBoard.assignTeams();
        return gameBoard;
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
    public GameBoards updateGameBoard(GameBoards gameBoard) {
        return gameBoardRepository.save(gameBoard);
    }

    private List<GameRow> createGameRows() {
        return IntStream.rangeClosed(1, 5)
                .mapToObj(i -> createGameRow())
                .collect(Collectors.toList());
    }

    private GameRow createGameRow() {
        return new GameRow(createGameTiles(5));
    }

    private List<GameTile> createGameTiles(int listSize){
        return IntStream.rangeClosed(1, listSize)
                .mapToObj(i -> new GameTile())
                .collect(Collectors.toList());
    }
}

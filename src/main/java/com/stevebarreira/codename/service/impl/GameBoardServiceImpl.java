package com.stevebarreira.codename.service.impl;

import com.stevebarreira.codename.model.GameBoards;
import com.stevebarreira.codename.model.GameRow;
import com.stevebarreira.codename.model.GameTile;
import com.stevebarreira.codename.model.WordList;
import com.stevebarreira.codename.repository.GameBoardRepository;
import com.stevebarreira.codename.service.GameBoardService;
import com.stevebarreira.codename.service.WordListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class GameBoardServiceImpl implements GameBoardService {

    @Autowired
    private GameBoardRepository gameBoardRepository;

    @Autowired
    private WordListService wordListService;

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
        List<GameRow> gameRows = new ArrayList<>();
        IntStream.rangeClosed(1, 5)
                .forEach(i -> gameRows.add(createGameRow()));
        return gameRows;
    }

    private GameRow createGameRow() {
        GameRow gameRow = new GameRow();
        List<GameTile> tiles = new ArrayList<>();
        IntStream.rangeClosed(1, 5)
                .forEach(i -> tiles.add(new GameTile()));
        gameRow.setRowTiles(tiles);
        return gameRow;
    }
}

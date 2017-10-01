package com.stevebarreira.codename.controller;

import com.stevebarreira.codename.model.GameBoards;
import com.stevebarreira.codename.model.WordList;
import com.stevebarreira.codename.service.GameBoardService;
import com.stevebarreira.codename.service.GameService;
import com.stevebarreira.codename.service.WordListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GameBoardController {

    @Autowired
    private WordListService wordListService;
    @Autowired
    private GameService gamesService;
    @Autowired
    private GameBoardService gameBoardService;

    @RequestMapping(method = RequestMethod.GET, value = "/gameboards", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GameBoards> getRandomGameBoard() {
        return gameBoardService.getAllGameBoards();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/gameboards", produces = MediaType.APPLICATION_JSON_VALUE)
    public GameBoards createGameBoard() {
        return gameBoardService.createRandomGameBoard();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/gameboards/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GameBoards getGameBoardById(@PathVariable(value = "id") String id) {
        return gameBoardService.getGameBoardById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/gameboards/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GameBoards getRandomGameBoard(@RequestBody GameBoards gameBoard) {
        return gameBoardService.updateGameBoard(gameBoard);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/wordlist", produces = MediaType.APPLICATION_JSON_VALUE)
    public WordList getAllWords() {
        return wordListService.getRandomWordList();
    }
}

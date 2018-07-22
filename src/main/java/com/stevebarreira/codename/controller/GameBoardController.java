package com.stevebarreira.codename.controller;

import com.stevebarreira.codename.model.GameBoards;
import com.stevebarreira.codename.model.WordList;
import com.stevebarreira.codename.service.GameBoardService;
import com.stevebarreira.codename.service.WordListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GameBoardController {

    @Autowired
    private WordListService wordListService;

    @Autowired
    private GameBoardService gameBoardService;

    @GetMapping(value = "/gameboards", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GameBoards> getAllGameBoards() {
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
    public GameBoards updateGameBoardById(@RequestBody GameBoards gameBoard) {
        return gameBoardService.updateGameBoard(gameBoard);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/wordlist", produces = MediaType.APPLICATION_JSON_VALUE)
    public WordList getAllWords() {
        return wordListService.getRandomWordList();
    }
}

package com.stevebarreira.codename.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stevebarreira.codename.model.GameBoards;
import com.stevebarreira.codename.model.WordList;
import com.stevebarreira.codename.model.dto.Codenames;
import com.stevebarreira.codename.repository.WordlistRepository;
import com.stevebarreira.codename.service.GameBoardService;
import com.stevebarreira.codename.service.GameService;
import com.stevebarreira.codename.service.WordListService;

@RestController
@RequestMapping("/api")
public class GameBoardController {
	
		@Autowired 
		private GameService gamesService;
		
		@Autowired
		private GameBoardService gameBoardService;
		
		@Autowired
		WordListService wordListService;
		
		@RequestMapping(method = RequestMethod.GET, value = "/gameboards",produces=MediaType.APPLICATION_JSON_VALUE)
		public List<GameBoards> getRandomGameBoard () {	
			return gameBoardService.getAllGameBoards();
		}
		
		@RequestMapping(method = RequestMethod.POST, value = "/gameboards",produces=MediaType.APPLICATION_JSON_VALUE)
		public GameBoards createGameBoard () {	
			return gameBoardService.createRandomGameBoard();
		}
		
		@RequestMapping(method = RequestMethod.GET, value = "/gameboards/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
		public GameBoards getGameBoardById (@PathVariable(value="id") String id) {	
			return gameBoardService.getGameBoardById(id);
		}
		
		@RequestMapping(method = RequestMethod.PUT, value = "/gameboards/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
		public GameBoards getRandomGameBoard (@RequestBody GameBoards gameBoard) {	
			return gameBoardService.updateGameBoard(gameBoard);
		}
		
		
		@RequestMapping(method = RequestMethod.GET, value ="/wordlist", produces=MediaType.APPLICATION_JSON_VALUE)
		public WordList getAllWords(){
			return wordListService.getRandomWordList();
		}
}

package com.stevebarreira.codename.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stevebarreira.codename.model.GameBoard;
import com.stevebarreira.codename.model.WordList;
import com.stevebarreira.codename.model.dto.Codenames;
import com.stevebarreira.codename.repository.WordlistRepository;
import com.stevebarreira.codename.service.GameBoardFactory;
import com.stevebarreira.codename.service.WordListService;

@RestController
@RequestMapping("/api")
public class GameBoardController {
	
		@Autowired 
		private GameBoardFactory gamesService;
		
		@Autowired
		WordListService wordListService;
		
		@RequestMapping(method = RequestMethod.GET, value = "/gameboards",produces=MediaType.APPLICATION_JSON_VALUE)
		public GameBoard getRandomGameBoard () {	
			return gamesService.createRandomGameBoard();
		}
		
		@RequestMapping(method = RequestMethod.GET, value ="/wordlist", produces=MediaType.APPLICATION_JSON_VALUE)
		public WordList getAllWords(){
			return wordListService.getRandomWordList();
		}
}

package com.stevebarreira.codename.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stevebarreira.codename.model.GameClue;
import com.stevebarreira.codename.model.Games;
import com.stevebarreira.codename.service.GameService;
import com.stevebarreira.codename.service.WordListService;

@RestController
@RequestMapping("/api")
public class GameController {
	
		@Autowired 
		private GameService gamesService;
		
		@Autowired
		WordListService wordListService;
		
		@Autowired
		private SimpMessagingTemplate template;
		
		@RequestMapping(method = RequestMethod.POST, value = "/games",produces=MediaType.APPLICATION_JSON_VALUE)
		public Games getNewGame () {	
			return gamesService.createNewGame();
		}
		
		@RequestMapping(method = RequestMethod.POST, value = "/games/{id}/rounds",produces=MediaType.APPLICATION_JSON_VALUE)
		public Games createNewRound(@PathVariable String id) {	
			return gamesService.newRoundForGame(id);
		}
		
		@RequestMapping(method = RequestMethod.POST, value = "/games/{id}/rounds/{roundNumber}/clues",produces=MediaType.APPLICATION_JSON_VALUE)
		public Games addGameClueToGameRound(@PathVariable String id, @PathVariable Integer roundNumber, @RequestBody GameClue gameClue) {		
			Games game = gamesService.addClueToGameRound(id, roundNumber, gameClue);
			if(game != null){
				template.convertAndSend("/topic/gamechange/" + id, gameClue);
			}
			return game;
			
		}
		
		@RequestMapping(method = RequestMethod.GET, value = "/games/{id}/rounds/{roundNumber}/clues",produces=MediaType.APPLICATION_JSON_VALUE)
		public List<GameClue> getGameCluesByGameAndRound(@PathVariable String id, @PathVariable Integer roundNumber){
			return gamesService.getCluesByGameAndRound(id, roundNumber);
		}
		
		@RequestMapping(method = RequestMethod.GET, value = "/games/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
		public Games getGameById(@PathVariable String id){
			return gamesService.getGameById(id);
		}
		
		@RequestMapping(method = RequestMethod.GET, value = "/games", produces=MediaType.APPLICATION_JSON_VALUE)
		public Page<Games> getAllGames(Pageable pageable){
			return gamesService.getAllGames(pageable);
		}
		
		@RequestMapping(method = RequestMethod.PUT, value = "/games/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
		public Games updateGame(@RequestBody Games game){
			return gamesService.updateGame(game);
		}
		
//		@RequestMapping(method = RequestMethod.GET, value ="/wordlist", produces=MediaType.APPLICATION_JSON_VALUE)
//		public WordList getAllWords(){
//			return wordListService.getRandomWordList();
//		}
}

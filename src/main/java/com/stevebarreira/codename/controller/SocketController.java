package com.stevebarreira.codename.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.stevebarreira.codename.model.GameClue;
import com.stevebarreira.codename.service.GameService;

@Controller
public class SocketController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SocketController.class);
	
	private static final String GAMECHANGE_SOCKET_INBOUND = "/gamechange/{id}/{roundNumber}";
	private static final String GAMECHANGE_SOCKET_OUTBOUND = "/topic/gamechange/{id}";
	
	private static final String CLUE_SOCKET_INBOUND = "/gameclues/{id}/{roundNumber}";
	private static final String CLUE_SOCKET_OUTBOUND = "/topic/gameclues/{id}";
	
	
	
	@Autowired
	GameService gameService;
	
	@MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public GameClue testSocket(GameClue message) throws Exception {
		LOGGER.info("Message Recieved");
        return new GameClue(message.getClue(), message.getNumberOfWords(), message.getTeam());
    }
	
	@MessageMapping(CLUE_SOCKET_INBOUND)
    @SendTo(CLUE_SOCKET_OUTBOUND)
    public GameClue testSocketID(@DestinationVariable String id, @DestinationVariable Integer roundNumber, @Payload GameClue message) throws Exception {
		LOGGER.debug("Message Recieved | Game ID : " + id + " | Round : " + roundNumber);
		LOGGER.debug(message.toString());
        GameClue gameClue =  new GameClue(message.getClue(), message.getNumberOfWords(), message.getTeam());
        gameService.addClueToGameRound(id, roundNumber, message);
        return gameClue;
    }
	
	@MessageMapping(GAMECHANGE_SOCKET_INBOUND)
    @SendTo(GAMECHANGE_SOCKET_OUTBOUND)
    public String updateGameBoard(@DestinationVariable String id, @DestinationVariable Integer roundNumber, @Payload String message) throws Exception {
		LOGGER.debug("Message Recieved Game Change | Game ID : " + id + " | Round : " + roundNumber);
        return "UPDATE";
    }
	
	
}

package com.stevebarreira.codename.service;
import java.util.concurrent.CountDownLatch

import com.stevebarreira.codename.service.impl.GameServiceImpl
import com.stevebarreira.codename.model.GameBoards
import com.stevebarreira.codename.model.GameClue
import com.stevebarreira.codename.model.GameRound
import com.stevebarreira.codename.model.Games
import com.stevebarreira.codename.model.WordList
import com.stevebarreira.codename.repository.*

import spock.lang.Specification;
import spock.lang.Unroll

@Unroll
class GameServiceSpec extends GameSpec {
	
	def wordListService = Mock(WordListService)
	def gameRepository = Mock(GamesRepository)
	def gameBoardRepository = Mock(GameBoardRepository)
	def gameService = new GameServiceImpl(
		wordListService: wordListService,
		gameRepository: gameRepository,
		gameBoardRepository: gameBoardRepository
	)
	
	def wordListOf25(){
		def array = new ArrayList<String>()		
		25.times{
			array << "TEST"	
		}
		
		return array
	}
	
	def "Gameboard Service"() {
		
		setup:
		def wordListTest = wordListOf25()
		
		when:
		def GameBoards gameBoard = gameService.createRandomGameBoard()
		
		then:
		1 * wordListService.getRandomWordList() >> new WordList(
				wordList: wordListTest
			)
		1 * gameBoardRepository.save(_) >> new GameBoards()
		gameBoard != null
		
	}
	
	def "Game Service - getCluesByGameAndRound - #secenario"(){
		
		setup:
		def roundNumber = 1;
		def gameId = "182973";
		//Games testGame = 
		
		when:
		def gameClues = gameService.getCluesByGameAndRound(gameId, roundNumber)
		
		then:
		1 * gameRepository.findOne(_) >> game
		gameClues == expected
		
		where:
		secenario               | game           | expected
		'No Game'    		    | null           | []
		'Game without Rounds'   | new Games()    | []
		'Game with Empty Round' | emptyGame      | []
		'Game with clues'       | defaultGame    | []
	}
	
	
	
}

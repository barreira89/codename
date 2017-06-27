package com.stevebarreira.codename.service;
import java.util.concurrent.CountDownLatch

import com.stevebarreira.codename.service.impl.GameServiceImpl
import com.stevebarreira.codename.model.GameBoards
import com.stevebarreira.codename.model.WordList
import com.stevebarreira.codename.repository.*

import spock.lang.Specification;
import spock.lang.Unroll

class GameServiceSpec extends Specification {
	
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
	
}

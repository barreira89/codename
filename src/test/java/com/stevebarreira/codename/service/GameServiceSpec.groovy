package com.stevebarreira.codename.service;
import java.awt.Component.BaselineResizeBehavior
import java.util.concurrent.CountDownLatch
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import com.stevebarreira.codename.service.impl.GameServiceImpl
import com.stevebarreira.codename.model.GameBoards
import com.stevebarreira.codename.model.GameClue
import com.stevebarreira.codename.model.GameRound
import com.stevebarreira.codename.model.Games
import com.stevebarreira.codename.model.WordList
import com.stevebarreira.codename.repository.*
import spock.lang.Shared
import spock.lang.Specification;
import spock.lang.Unroll

@Unroll
class GameServiceSpec extends GameSpec {
	
	WordListService mockWordListService = Mock()
	GamesRepository mockGameRepository = Mock()
	GameBoardRepository mockGameBoardRepository = Mock()
	GameBoardService mockGameBoardService = Mock()
	
	GameService gameService = new GameServiceImpl(
		wordListService: mockWordListService,
		gameRepository: mockGameRepository,
		gameBoardRepository: mockGameBoardRepository,
		gameBoardService: mockGameBoardService
	)
			
	def 'getCluesByGameAndRound - #secenario'(){
		setup:
		def gameId = "182973";
		def targetRoundNumber = roundNumber;
	
		when:
		List<GameClue> gameClues = gameService.getCluesByGameAndRound(gameId, targetRoundNumber)
		
		then:
		1 * mockGameRepository.findOne(_) >> game
		gameClues == expected
		
		where:
		secenario                   | game           |roundNumber | expected
		'No Game'    		        | null           |1           | []
		'Game without Rounds'       | new Games()    |1           | []
		'Game with Empty Round'     | emptyGame      |1           | []
		'Game with clues'           | defaultGame    |1           | defaultGameClues
		'Game with different round' | defaultGame    |2           | []
	}
	
	def 'createNewGame - #scenario'(){
		setup:
		Games gameToCreate = new Games()
		
		when:
		Games resultGame = gameService.createNewGame()
		
		then:
		1 * mockGameRepository.save(_ as Games) >> gameRepoResult
		1 * mockGameBoardService.createRandomGameBoard() >> new GameBoards()
		0 * _
		
		and:
		resultGame == expected
		
		where:
		scenario 			     | gameRepoResult | wordListResult  | gameBoardRepoResult | expected
		'Happy Path' 		     | defaultGame    | defaultWordList | new GameBoards()    | defaultGame
		'Nothing from Repo'      | null           | defaultWordList | null                | null
		'Nothing from Game Repo' | null           | defaultWordList | new GameBoards()    | null
	}
	
	def 'getAfrellGames - #scenario'(){
		setup:
		
		when:
		Page<Games> resultGames = gameService.getAllGames(pageable)
		
		then:
		1 * mockGameRepository.findAll(_ as Pageable) >> gameRepoResult
		
		and:
		resultGames
		resultGames.content == expected
		resultGames.content.size() == expectedSize
		
		where:
		scenario 			     | gameRepoResult   | pageable                  | expected      | expectedSize
		'Happy Path' 		     | defaultPageGames | new PageRequest(0, 2)     | [defaultGame] | 1
		'Mutiple Page'           | listOf4PageGames | new PageRequest(0, 1)     | listOf4Games  | 4
	}
	
	
	
}

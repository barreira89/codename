package com.stevebarreira.codename.service;
import java.awt.Component.BaselineResizeBehavior
import java.sql.Savepoint
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
		secenario                       | game           |roundNumber | expected
		'No Game'    		            | null           |1           | []
		'Game without Rounds'           | new Games()    |1           | []
		'Game with Empty Round'         | emptyGame      |1           | []
		'Game with clues'               | defaultGame    |1           | defaultGameClues
		'Game with different round'     | defaultGame    |2           | []
		'Game with round without Clues' | defaultGame    |3           | []
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

	def 'getAllGames - #scenario'(){
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
		'Happy Path' 		     | defaultPageGames | new PageRequest(0, 2)     | [defaultGame]| 1
		'Mutiple Page'           | listOf4PageGames | new PageRequest(0, 1)     | listOf4Games  | 4
	}

	def 'newRoundForGame - #scenario'(){
		setup:
		Games gameWithOneRound = new Games(
				rounds: [
					new GameRound(
					roundNumber:startingRoundNumber
					)]
				)

		when:
		Games resultGame = gameService.newRoundForGame(_ as String)

		then:
		1 * mockGameRepository.findOne(_ as String) >> gameWithOneRound
		1 * mockGameRepository.save(_ as Games) >> gameWithOneRound
		1 * mockGameBoardService.createRandomGameBoard() >> defaultGameBoard
		0 * _

		and:
		resultGame
		resultGame.rounds.size() == 2
		resultGame.rounds[1].roundNumber == newlyAddedRoundNumber

		where:
		scenario  | startingRoundNumber || newlyAddedRoundNumber
		'Round 1' |  1                  || 2
		'Round 32'| 32                  || 33
	}

	def 'addClueToGameRound - #scenario'(){
		when:
		Games resultGame = gameService.addClueToGameRound(gameId, roundNumber, gameClue)

		then:
		1 * mockGameRepository.findOne(_ as String) >> defaultGame
		1 * mockGameRepository.save(_ as Games) >> defaultGame
		0 * _

		and:
		resultGame
		resultGame.rounds[0]
		resultGame.rounds[0].gameClues.size == clueListSize

		where:
		scenario     | gameId | roundNumber | gameClue                                                 || clueListSize
		'Happy Path' | '1'    | 1           | new GameClue(clue: "TEST", numberOfWords: 8,team: "RED") || 3
		'Null Clue'  | '1'    | 1           | null                                                     || 3  
		'No Round'   |  '1'   | 45          | new GameClue(clue: "TEST", numberOfWords: 5,team: "RED") || 3

	}
}

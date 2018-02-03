package com.stevebarreira.codename.service

import com.stevebarreira.codename.model.GameBoards
import com.stevebarreira.codename.model.GameClue
import com.stevebarreira.codename.model.GameRound
import com.stevebarreira.codename.model.Games
import com.stevebarreira.codename.model.transformer.impl.GamesTransformer
import com.stevebarreira.codename.model.validator.Validator
import com.stevebarreira.codename.model.validator.impl.ClueValidator
import com.stevebarreira.codename.repository.GamesRepository
import com.stevebarreira.codename.service.impl.GameServiceImpl
import org.springframework.boot.context.config.ResourceNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class GameServiceSpec extends Specification implements GameSpec {

    GamesRepository mockGameRepository = Mock()
    GameBoardService mockGameBoardService = Mock()
    Validator<GameClue> mockClueValidator = Mock()
    GamesTransformer transformer = new GamesTransformer()

    GameService gameService = new GameServiceImpl(
            gameRepository: mockGameRepository,
            gameBoardService: mockGameBoardService,
            gamesTransformer: transformer,
            clueValidator: mockClueValidator
    )

    def 'getCluesByGameAndRound - #secenario'() {
        setup:
        def gameId = "182973"
        def targetRoundNumber = roundNumber

        when:
        List<GameClue> gameClues = gameService.getCluesByGameAndRound(gameId, targetRoundNumber)

        then:
        1 * mockGameRepository.findOne(_) >> game
        gameClues == expected

        where:
        secenario                       | game        | roundNumber | expected
        'No Game'                       | null        | 1           | []
        'Game without Rounds'           | new Games() | 1           | []
        'Game with Empty Round'         | emptyGame   | 1           | []
        'Game with clues'               | defaultGame | 1           | defaultGameClues
        'Game with different round'     | defaultGame | 2           | []
        'Game with round without Clues' | defaultGame | 3           | []
    }

    def 'createNewGame - #scenario'() {
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
        scenario                 | gameRepoResult | wordListResult  | gameBoardRepoResult | expected
        'Happy Path'             | defaultGame    | defaultWordList | new GameBoards()    | defaultGame
        'Nothing from Repo'      | null           | defaultWordList | null                | null
        'Nothing from Game Repo' | null           | defaultWordList | new GameBoards()    | null
    }

    def 'getAllGames - #scenario'() {
        when:
        Page<Games> resultGames = gameService.getAllGames(pageable)

        then:
        1 * mockGameRepository.findAll(_ as Pageable) >> gameRepoResult

        and:
        resultGames
        resultGames.content == expected
        resultGames.content.size() == expectedSize

        where:
        scenario       | gameRepoResult   | pageable              | expected      | expectedSize
        'Happy Path'   | defaultPageGames | new PageRequest(0, 2) | [defaultGame] | 1
        'Mutiple Page' | listOf4PageGames | new PageRequest(0, 1) | listOf4Games  | 4
    }

    def 'newRoundForGame - #scenario'() {
        setup:
        Games gameWithOneRound = new Games(
                rounds: [
                        new GameRound(
                                roundNumber: startingRoundNumber
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
        scenario   | startingRoundNumber || newlyAddedRoundNumber
        'Round 1'  | 1                   || 2
        'Round 32' | 32                  || 33
    }

    def 'addClueToGameRound - #scenario'() {
        when:
        Games resultGame = gameService.addClueToGameRound(gameId, roundNumber, gameClue)

        then:
        1 * mockGameRepository.findOne(_ as String) >> defaultGame
        1 * mockGameRepository.save(_ as Games) >> defaultGame
        1 * mockClueValidator.isValid(_ as GameClue) >> true
        0 * _

        and:
        resultGame
        resultGame.rounds[0]
        resultGame.rounds[0].gameClues.size == clueListSize

        where:
        scenario     | gameId | roundNumber | gameClue                                                  || clueListSize
        'Happy Path' | '1'    | 1           | new GameClue(clue: "TEST", numberOfWords: 8, team: "RED") || 3
        'No Round'   | '1'    | 45          | new GameClue(clue: "TEST", numberOfWords: 5, team: "RED") || 2

    }

    def 'addClueToGameRound - null clue'() {
        when:
        Games resultGame = gameService.addClueToGameRound(gameId, roundNumber, gameClue)

        then:
        0 * _

        and:
        thrown(IllegalArgumentException)

        where:
        gameId | roundNumber | gameClue || clueListSize
        '1'    | 1           | null     || 2

    }

    def 'addClueToGameRound - negative - #scenario'() {
        when:
        Games resultGame = gameService.addClueToGameRound(gameId, roundNumber, gameClue)


        then:
        1 * mockGameRepository.findOne(_ as String) >> gameFromRepo
        1 * mockClueValidator.isValid(_ as GameClue) >> true
        0 * _

        and:
        !resultGame

        where:
        scenario                     | gameId | roundNumber | gameClue                                                  | gameFromRepo | result
        'Nothing Returned From Repo' | '1'    | 1           | new GameClue(clue: "TEST", numberOfWords: 8, team: "RED") | null         | true

    }

    def 'addClueToGameRound - empty rounds - #scenario'() {
        setup:
        defaultGame.rounds = null

        when:
        Games resultGame = gameService.addClueToGameRound(gameId, roundNumber, gameClue)


        then:
        1 * mockGameRepository.findOne(_ as String) >> defaultGame
        1 * mockGameRepository.save(_ as Games) >> defaultGame
        1 * mockClueValidator.isValid(_ as GameClue) >> true
        0 * _

        and:
        resultGame
        !resultGame.rounds

        where:
        scenario                     | gameId | roundNumber | gameClue                                                  | gameFromRepo | result
        'Nothing Returned From Repo' | '1'    | 1           | new GameClue(clue: "TEST", numberOfWords: 8, team: "RED") | null         | true

    }

    def 'getGameById - #scenario'() {
        when:
        String gameId = "id"
        Games resultGame = gameService.getGameById(gameId)

        then:
        1 * mockGameRepository.findOne(_ as String) >> resultFromRepo
        0 * _

        and:
        resultGame == resultFromRepo

        where:
        scenario                   | resultFromRepo | expectedResult
        'Happy Path - Result'      | defaultGame    | defaultGame
    }

    def 'getGameById - not Found'() {
        when:
        String gameId = "id"
        Games resultGame = gameService.getGameById(gameId)

        then:
        1 * mockGameRepository.findOne(_ as String) >> resultFromRepo
        0 * _

        and:
        thrown(EntityNotFoundException)

        where:
        scenario                   | resultFromRepo
        'Happy Path - Null Result' | null
    }

    def 'updateGame - #scenario'() {
        when:
        String gameId = "id"
        Games resultGame = gameService.updateGame(gameToUpdate)

        then:
        1 * mockGameRepository.save(gameToUpdate) >> gameToUpdate
        0 * _

        and:
        resultGame == gameToUpdate

        where:
        scenario                   | gameToUpdate
        'Happy Path - Result'      | defaultGame
        'Happy Path - Null Result' | null
    }

    def 'delelte game'() {
        when:
        String gameId = "id"
        gameService.deleteGameById(gameId)

        then:
        1 * mockGameRepository.delete(_ as String)
        0 * _

    }
    
}

package com.stevebarreira.codename.service

import com.stevebarreira.codename.model.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import spock.lang.Shared

trait GameSpec {

    /*
     * Games objects used for testing
     */

    @Shared
    Games emptyGame = new Games(
            rounds: [new GameRound()]
    )

    @Shared
    List<GameClue> defaultGameClues = [
            new GameClue(
                    numberOfWords: 2,
                    clue: 'TEST CLUE',
                    team: 'RED'
            ),
            new GameClue(
                    numberOfWords: 2,
                    clue: 'TEST CLUE',
                    team: 'RED'
            )
    ]

    @Shared
    Games defaultGame = new Games(
            rounds: [
                    new GameRound(
                            id: '123123123',
                            roundNumber: 1,
                            gameClues: defaultGameClues,
                            gameBoard: defaultGameBoard
                    ),
                    new GameRound(
                            id: '99',
                            roundNumber: 3
                    )

            ]
    )


    def wordListOf25() {
        def array = new ArrayList<String>()
        25.times {
            array << "TEST_" + it
        }

        return array
    }

    @Shared
    List<Games> listOf4Games = [defaultGame, defaultGame, defaultGame, defaultGame]

    def gameListOf4() {
        def array = new ArrayList<Games>()
        4.times {
            array << defaultGame
        }
        return array
    }

    @Shared
    def defaultWordList = new WordList(
            wordList: ["TEST", "TEST1"]
    )

    @Shared
    GameBoards defaultGameBoard = new GameBoards(
            leadTeam: Team.RED.getValueOf(),
            gameRows: [new GameRow(), new GameRow()],
            wordList: defaultWordList
    )


    @Shared
    Page<Games> defaultPageGames = new PageImpl<Games>([defaultGame], new PageRequest(0, 1), 1)

    @Shared
    Page<Games> listOf4PageGames = new PageImpl<Games>(listOf4Games, new PageRequest(0, 1), 4)

    @Shared
    TeamList defaultTeamList = new TeamList(
            teamList: [
                    Team.RED,
                    Team.RED,
                    Team.RED,
                    Team.RED,
                    Team.RED,
                    Team.RED,
                    Team.RED,
                    Team.RED,
                    Team.RED,
                    Team.BLUE,
                    Team.BLUE,
                    Team.BLUE,
                    Team.BLUE,
                    Team.BLUE,
                    Team.BLUE,
                    Team.BLUE,
                    Team.ASSASSIN,
                    Team.NEUTRAL,
                    Team.NEUTRAL,
                    Team.NEUTRAL,
                    Team.NEUTRAL,
                    Team.NEUTRAL,
                    Team.NEUTRAL,
                    Team.NEUTRAL,
                    Team.NEUTRAL,
                    Team.NEUTRAL,
            ]
    )

}

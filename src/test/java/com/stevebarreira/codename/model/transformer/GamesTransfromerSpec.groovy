package com.stevebarreira.codename.model.transformer

import com.stevebarreira.codename.model.GameBoards
import com.stevebarreira.codename.model.GameRound
import com.stevebarreira.codename.model.Games
import com.stevebarreira.codename.model.dto.GamesDto
import com.stevebarreira.codename.model.transformer.impl.GamesTransformer
import spock.lang.Shared
import spock.lang.Specification


class GamesTransfromerSpec extends Specification {

    GamesTransformer transformer = new GamesTransformer()

    @Shared
    Games inputGames

    def setupSpec(){
        inputGames = new Games(
                id: "ID12312",
                rounds: [
                        new GameRound(
                                gameBoard: new GameBoards(
                                        id: "GAMEBOARD ID1"
                                )
                        ),
                        new GameRound(
                                gameBoard: new GameBoards(
                                        id: "GAMEBOARDID2"
                                )
                        )
                ]
        )
    }

    def 'Happy Path Transform'(){
        when:
        GamesDto responseDto = transformer.transform(inputGames)

        then:
        responseDto
        responseDto.id == inputGames.id
        responseDto.gameBoards
        responseDto.gameBoards.size() == 2
        responseDto.gameRounds
        responseDto.gameRounds.size() == 2
    }

    def 'Happy Path Transform - List'(){
        when:
        List<GamesDto> responseDtoList = transformer.transform([inputGames,inputGames])

        then:
        responseDtoList
        responseDtoList.size() == 2
        responseDtoList.id.size() == 2
    }

    def 'No game Board In Round'() {
        when:
        Games gameWithoutGameBoard = inputGames.clone()
        gameWithoutGameBoard.rounds[0].gameBoard = null
        GamesDto response = transformer.transform(gameWithoutGameBoard)
        gameWithoutGameBoard.rounds.remove(0)
        GamesDto response2 = transformer.transform(gameWithoutGameBoard)

        then:
        response
        response2

    }

}
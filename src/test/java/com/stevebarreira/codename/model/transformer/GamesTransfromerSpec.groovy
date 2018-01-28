package com.stevebarreira.codename.model.transformer

import com.stevebarreira.codename.model.GameBoards
import com.stevebarreira.codename.model.GameRound
import com.stevebarreira.codename.model.Games
import com.stevebarreira.codename.model.dto.GamesDto
import com.stevebarreira.codename.model.transformer.impl.GamesTransformer
import spock.lang.Specification


class GamesTransfromerSpec extends Specification {

    GamesTransformer transformer = new GamesTransformer()

    def 'Happy Path Transform'(){
        given:
        Games gamesInput = new Games(
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

        when:
        GamesDto responseDto = transformer.transform(gamesInput)

        then:
        responseDto
        responseDto.id == gamesInput.id
        responseDto.gameBoards
        responseDto.gameBoards.size() == 2
        responseDto.gameRounds
        responseDto.gameRounds.size() == 2
    }

}
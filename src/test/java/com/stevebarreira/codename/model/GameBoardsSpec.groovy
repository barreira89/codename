package com.stevebarreira.codename.model

import spock.lang.Specification

class GameBoardsSpec extends Specification {

    def 'getId '() {
        when:
        GameBoards gameBoards = new GameBoards(
                id: id
        )

        gameBoards.getId()

        then:
        gameBoards.id
        gameBoards.id.length() == 36

        where:
        scenario       | id
        'ID Present'   | UUID.randomUUID()
        'ID generated' | null
    }
}

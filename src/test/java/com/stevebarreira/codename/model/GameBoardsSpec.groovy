package com.stevebarreira.codename.model

import spock.lang.Ignore
import spock.lang.Specification

class GameBoardsSpec extends Specification{

    @Ignore
    def 'assignTeams'(){
        when:
        GameBoards gameBoards = new  GameBoards()

        then:
        gameBoards
        gameBoards.getLeadTeam()


    }
}

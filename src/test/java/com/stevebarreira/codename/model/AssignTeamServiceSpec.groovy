package com.stevebarreira.codename.model

import spock.lang.Specification

class AssignTeamServiceSpec extends Specification {

    def "CreateGameRows"() {
        when:
        int size = 5
        List<GameRow> resultGameRow = AssignTeamService.createGameRows(size)

        then:
        resultGameRow
        resultGameRow.size() == size
        resultGameRow[0].rowTiles
        resultGameRow[0].rowTiles.size() == size

    }
}

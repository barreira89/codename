package com.stevebarreira.codename.model

import com.stevebarreira.codename.service.GameSpec
import com.stevebarreira.codename.service.impl.AssignTeamServiceImpl
import spock.lang.Specification

class AssignTeamServiceSpec extends Specification implements GameSpec {

    AssignTeamServiceImpl assignTeamService = new AssignTeamServiceImpl()

    def "createGameRows"() {
        when:
        List<GameRow> resultGameRow = assignTeamService.createGameRows(5)

        then:
        resultGameRow
        resultGameRow.size() == 5
        resultGameRow[0].rowTiles
        resultGameRow[0].rowTiles.size() == 5
    }

    def "assignTeams"() {
        when:
        List<GameRow> results = assignTeamService.getAssignedTeams(new TeamList(), new WordList())

        then:
        results
        results.size() == 5
        results.rowTiles
        results.rowTiles.flatten().size() == 25
        results.rowTiles.team.flatten().containsAll([Team.RED, Team.ASSASSIN, Team.BLUE, Team.NEUTRAL])

    }
}

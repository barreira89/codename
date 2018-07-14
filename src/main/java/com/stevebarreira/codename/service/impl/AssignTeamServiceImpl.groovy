package com.stevebarreira.codename.service.impl

import com.stevebarreira.codename.model.GameRow
import com.stevebarreira.codename.model.GameTile
import com.stevebarreira.codename.model.TeamList
import com.stevebarreira.codename.model.WordList
import com.stevebarreira.codename.service.AssignTeamService
import org.springframework.stereotype.Service

@Service
class AssignTeamServiceImpl implements AssignTeamService {

    List<GameRow> getAssignedTeams(TeamList teamList, WordList wordList) {
        assignTeams(teamList, wordList, createGameRows(5))
    }

    List<GameRow> assignTeams(TeamList teamList, WordList wordList, List<GameRow> gameRows) {
        gameRows.eachWithIndex { GameRow gameRow, int gameRowIndex ->
            gameRow.rowTiles.eachWithIndex { GameTile gameTile, int colIndex ->
                gameTile.colIndex = colIndex
                gameTile.rowIndex = gameRowIndex
                gameTile.team = teamList.getRandomTeam()
                gameTile.word = wordList.getRandomWord()
            }
        }
    }

    private static List<GameRow> createGameRows(int size) {
        (1..size).collect { row ->
            new GameRow(
                    rowTiles: (1..size).collect { col ->
                        new GameTile(
                                rowIndex: row - 1,
                                colIndex: col - 1
                        )
                    })
        }
    }
}

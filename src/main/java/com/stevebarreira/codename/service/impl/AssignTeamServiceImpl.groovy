package com.stevebarreira.codename.service.impl

import com.stevebarreira.codename.model.*
import com.stevebarreira.codename.service.AssignTeamService
import com.stevebarreira.codename.service.WordListService
import groovy.transform.Synchronized
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AssignTeamServiceImpl implements AssignTeamService {

    @Autowired
    WordListService wordListService

    List<GameRow> getAssignedTeams(TeamList teamList, WordList wordList) {
        assignTeams(teamList, wordList, createGameRows(5))
    }

    @Synchronized
    List<GameRow> assignTeams(TeamList teamList, WordList wordList, List<GameRow> gameRows) {
        List<Word> selectionList = wordList.getWords()
        gameRows.eachWithIndex { GameRow gameRow, int gameRowIndex ->
            gameRow.rowTiles.eachWithIndex { GameTile gameTile, int colIndex ->
                gameTile.colIndex = colIndex
                gameTile.rowIndex = gameRowIndex
                gameTile.team = teamList.getRandomTeam()
                gameTile.word = selectionList.pop()
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

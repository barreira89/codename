package com.stevebarreira.codename.service.impl

import com.stevebarreira.codename.model.GameRow
import com.stevebarreira.codename.model.GameTile
import com.stevebarreira.codename.model.TeamList
import com.stevebarreira.codename.model.Word
import com.stevebarreira.codename.model.WordList
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
                gameTile.word = selectionList[gameRowIndex * gameRow.rowTiles.size() + colIndex]
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

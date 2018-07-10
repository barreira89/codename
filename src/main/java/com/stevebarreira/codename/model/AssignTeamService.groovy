package com.stevebarreira.codename.model

class AssignTeamService {

    static List<GameRow> createGameRows(int size) {
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

    static List<GameRow> getAssignedTeams(TeamList teamList, WordList wordList){
        assignTeams(teamList, wordList, createGameRows(5))
    }

    static List<GameRow> assignTeams(TeamList teamList, WordList wordList, List<GameRow> gameRows) {
        gameRows.eachWithIndex { GameRow gameRow, int gameRowIndex ->
            gameRow.rowTiles.eachWithIndex { GameTile gameTile, int colIndex ->
                gameTile.colIndex = colIndex
                gameTile.rowIndex = gameRowIndex
                gameTile.team = teamList.getRandomTeam()
                gameTile.word = wordList.getRandomWord()
            }
        }
    }
}

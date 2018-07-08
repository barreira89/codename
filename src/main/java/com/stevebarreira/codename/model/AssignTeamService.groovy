package com.stevebarreira.codename.model

class AssignTeamService {

    static List<GameRow> assignTeams(TeamList teamList, WordList wordList, List<GameRow> gameRows){
        gameRows.eachWithIndex{ GameRow gameRow, int gameRowIndex ->
            gameRow.rowTiles.eachWithIndex{ GameTile gameTile, int colIndex ->
                gameTile.colIndex = colIndex
                gameTile.rowIndex = gameRowIndex
                gameTile.team = teamList.getRandomTeam()
                gameTile.word = wordList.getRandomWord()
            }
        }
    }
}

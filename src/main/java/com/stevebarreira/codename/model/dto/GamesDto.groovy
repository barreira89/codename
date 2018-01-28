package com.stevebarreira.codename.model.dto

import com.stevebarreira.codename.model.GameBoards
import com.stevebarreira.codename.model.GameRound
import com.stevebarreira.codename.model.GameStatus

class GamesDto {

    String id
    GameStatus status
    List<GameRound> gameRounds
    List<GameBoards> gameBoards

}

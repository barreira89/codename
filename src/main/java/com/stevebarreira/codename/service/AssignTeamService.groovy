package com.stevebarreira.codename.service

import com.stevebarreira.codename.model.GameRow
import com.stevebarreira.codename.model.TeamList
import com.stevebarreira.codename.model.WordList

interface AssignTeamService {

    List<GameRow> getAssignedTeams(TeamList teamList, WordList wordList)

    List<GameRow> assignTeams(TeamList teamList, WordList wordList, List<GameRow> gameRows)

}
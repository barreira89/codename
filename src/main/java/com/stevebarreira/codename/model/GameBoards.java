package com.stevebarreira.codename.model;

import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

public class GameBoards implements Cloneable {

    @Id
    private String id;
    private String leadTeam;
    private List<GameRow> gameRows;
    private WordList wordList;
    private TeamList teamList;

    public GameBoards(TeamList teamList){
        this.teamList = teamList;
    }

    public GameBoards(TeamList teamList, WordList wordList, List<GameRow> gameRows){
        this.teamList = teamList;
        this.wordList = wordList;
        this.gameRows = gameRows;
    }

    public GameBoards() {
        this.teamList = new TeamList();
    }

    public String getId() {
        return id == null ? UUID.randomUUID().toString() : this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLeadTeam() {
        return leadTeam;
    }

    public void setLeadTeam(String leadTeam) {
        this.leadTeam = leadTeam;
    }

    public List<GameRow> getGameRows() {
        return gameRows;
    }

    public void setGameRows(List<GameRow> gameRows) {
        this.gameRows = gameRows;
    }

    public WordList getWordList() {
        return wordList;
    }

    public void setWordList(WordList wordList) {
        this.wordList = wordList;
    }

}

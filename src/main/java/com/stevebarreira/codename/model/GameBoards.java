package com.stevebarreira.codename.model;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

public class GameBoards implements Cloneable {

    @Id
    private String id;
    private String leadTeam;
    private List<GameRow> gameRows;
    private WordList wordList;
    private TeamList teamList;

    public GameBoards() {
        this.teamList = new TeamList();
        this.gameRows = createGameRows();
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

    public void assignTeams() {
        this.setGameRows(AssignTeamService.assignTeams(teamList, wordList, this.gameRows));
    }

    public WordList getWordList() {
        return wordList;
    }

    public void setWordList(WordList wordList) {
        this.wordList = wordList;
    }

    private List<GameRow> createGameRows() {
        List<GameRow> gameRows = new ArrayList<>();
        IntStream.rangeClosed(1, 5)
                .forEach(i -> gameRows.add(createGameRow()));
        return gameRows;
    }

    private GameRow createGameRow() {
        GameRow gameRow = new GameRow();
        List<GameTile> tiles = new ArrayList<>();
        IntStream.rangeClosed(1, 5)
                .forEach(i -> tiles.add(new GameTile()));
        gameRow.setRowTiles(tiles);
        return gameRow;
    }
}

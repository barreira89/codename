package com.stevebarreira.codename.model;

import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.util.Assert;

public class GameBoards {
	
	@Id
	private String id;
	private String leadTeam; 
	List<GameRow> gameRows;
	private WordList wordList;
	private TeamList teamList;
	
	public GameBoards(){
		this.teamList = new TeamList();
	}
	public String getId() {
			return id == null ? UUID.randomUUID().toString(): this.id;
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
		for(int i = 0; i < gameRows.size(); i++){
			for(int x = 0; x < gameRows.get(i).getRowTiles().size(); x ++ ){
				gameRows.get(i).getRowTiles().get(x).setTeam(teamList.getRandomTeam());
				gameRows.get(i).getRowTiles().get(x).setRowIndex(i);
				gameRows.get(i).getRowTiles().get(x).setColIndex(x);
				gameRows.get(i).getRowTiles().get(x).setWord(wordList.getRandomWord());
			}
		}
		this.leadTeam = teamList.getLeadTeam();
	}
	
	public WordList getWordList() {
		return wordList;
	}
	public void setWordList(WordList wordList) {
		this.wordList = wordList;
	}  
}

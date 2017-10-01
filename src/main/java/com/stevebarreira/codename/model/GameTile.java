package com.stevebarreira.codename.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

public class GameTile {
	
	@Id
	@JsonIgnore
	private String id;
	private String word;
	private Team team;

	private int rowIndex;
	private int colIndex;
	private boolean selected;
	
	public GameTile(){
		//Replace with Random Word
		this.word = "TEST WORD 1";
	}
	
	
	public GameTile(String word){
		this.word = word;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
	public int getRowIndex() {
		return rowIndex;
	}


	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}


	public int getColIndex() {
		return colIndex;
	}


	public void setColIndex(int colIndex) {
		this.colIndex = colIndex;
	}

	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	

}

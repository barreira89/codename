package com.stevebarreira.codename.model;

public class GameClue {
	
	private String clue;
	private Integer numberOfWords;
	private String team;
	
	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getClue() {
		return clue;
	}

	public void setClue(String clue) {
		this.clue = clue;
	}

	public Integer getNumberOfWords() {
		return numberOfWords;
	}

	public void setNumberOfWords(Integer numberOfWords) {
		this.numberOfWords = numberOfWords;
	}

	public GameClue(){}

	public GameClue(String clue, Integer numberOfWords, String team) {
		super();
		this.clue = clue;
		this.numberOfWords = numberOfWords;
		this.team = team;
	}
	
	@Override
	public String toString(){
		if(numberOfWords != null && clue != null){
			return "Clue: " + clue + " | Words " + numberOfWords;
		}
		return "";
	}
}

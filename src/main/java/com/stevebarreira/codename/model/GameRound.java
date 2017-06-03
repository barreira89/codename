package com.stevebarreira.codename.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class GameRound {
	
	@Id
	private String id;
	private int roundNumber;
	@DBRef
	private GameBoards gameBoard;
	private List<GameClue> gameClues; 

	public List<GameClue> getGameClues() {
		return gameClues;
	}

	public void setGameClues(List<GameClue> gameClues) {
		this.gameClues = gameClues;
	}

	public void addClue(GameClue gameClue){
		if(gameClues == null){
			this.gameClues = new ArrayList<GameClue>();
		}
		gameClues.add(gameClue);
	}
	
	public GameRound(){}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRoundNumber() {
		return roundNumber;
	}

	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}

	public GameBoards getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(GameBoards gameBoard) {
		this.gameBoard = gameBoard;
	}
}

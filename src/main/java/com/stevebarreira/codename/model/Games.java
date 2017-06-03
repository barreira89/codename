package com.stevebarreira.codename.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Games {
	
	@Id
	public String id;
	private GameStatus status;
	private List<GameRound> rounds;
	private int blueScore;
	private int redScore;
	
	
	public Games(){
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	public void addGameRound(GameRound gameRound){
		this.rounds.add(gameRound);
	}

	public String getStatus() {
		return (null != status) ? status.getStatus() : "INACTIVE";
	}

	public void setStatus(String status) {
		this.status = GameStatus.fromValue(status);
	}


	public List<GameRound> getRounds() {
		return rounds;
	}


	public void setRounds(List<GameRound> rounds) {
		this.rounds = rounds;
	}


	public int getBlueScore() {
		return blueScore;
	}


	public void setBlueScore(int blueScore) {
		this.blueScore = blueScore;
	}


	public int getRedScore() {
		return redScore;
	}


	public void setRedScore(int redScore) {
		this.redScore = redScore;
	}

}

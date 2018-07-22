package com.stevebarreira.codename.model;

import org.apache.commons.collections4.CollectionUtils;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Games implements Cloneable {
	
	private static final String DEFAULT_STATUS = "ACTIVE";
	
	@Id
	public String id;
	private GameStatus status;
	private List<GameRound> rounds;
	private int blueScore;
	private int redScore;

	//Not Working Yet
	@CreatedDate
	private DateTime createdDate;
	
	@LastModifiedDate
	private DateTime modifiedDate;
	

	public void setStatus(GameStatus status) {
		this.status = status;
	}

	public Games(){
	}

	public GameRound getRoundByRoundNumber(Integer roundNumber){
		if(CollectionUtils.isNotEmpty(rounds)){
			return rounds.stream()
					.filter(gameRound -> gameRound.getRoundNumber() == roundNumber)
					.findFirst().orElse(null);
		} else {
			return null;
		}
	}

	public DateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(DateTime createdDate) {
		this.createdDate = createdDate;
	}

	public DateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(DateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
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
		return (null != status) ? status.getStatus() : DEFAULT_STATUS;
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

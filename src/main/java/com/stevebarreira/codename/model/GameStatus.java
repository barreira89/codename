package com.stevebarreira.codename.model;

public enum GameStatus {
	ACTIVE("ACTIVE"), INACTIVE("INACTIVE");
	
	private final String value;
	
	GameStatus(String value){
		this.value = value;
	}
	
	public String getStatus(){
		return this.value;
	}
	
	public static GameStatus fromValue(String value){
		if(value != null){
			for(GameStatus status : values()){
				if(status.value.equals(value)){
					return status;
				}
			}
		}
		
		return getDefault();
	}
	
	private static GameStatus getDefault(){
		return INACTIVE;
	}
}

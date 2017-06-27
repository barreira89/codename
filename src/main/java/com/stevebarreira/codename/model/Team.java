package com.stevebarreira.codename.model;

public enum Team {
	RED("RED"), BLUE("BLUE"), NEUTRAL("NEUTRAL"), ASSASSIN("ASSASSIN");
	
	private final String value;
	
	Team(String value){
		this.value = value;
	}
	
	public String getValueOf(){
		return this.value;
	}
}

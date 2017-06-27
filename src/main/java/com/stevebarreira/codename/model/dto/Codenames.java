package com.stevebarreira.codename.model.dto;

import java.util.List;
import org.springframework.data.annotation.Id;

public class Codenames {
	

	@Id
	String id;
	List<String> words;
	
	public Codenames(){};
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<String> getWords() {
		return words;
	}
	public void setWords(List<String> words) {
		this.words = words;
	}

}

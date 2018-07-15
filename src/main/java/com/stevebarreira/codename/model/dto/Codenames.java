package com.stevebarreira.codename.model.dto;

import com.stevebarreira.codename.model.Word;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.stream.Collectors;

public class Codenames {

	@Id
	String id;
	List<String> words;
	
	public Codenames(){}

	public Codenames(List<Word> wordList){
		this.words = wordList.stream().map(Word::getValue).collect(Collectors.toList());
	}
	
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

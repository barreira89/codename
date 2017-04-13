package com.stevebarreira.codename.service;

import java.util.List;

import com.stevebarreira.codename.model.WordList;

public interface WordListService {
	
	public List<String> getRandomWordListString();
	
	public WordList getRandomWordList();
	
	public List<String> getRandomWordList(Integer size);

}

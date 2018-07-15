package com.stevebarreira.codename.service;

import com.stevebarreira.codename.model.Word;
import com.stevebarreira.codename.model.WordList;

public interface WordListService {
		
	WordList getRandomWordList();

	Word getRandomWord(WordList wordList);

}

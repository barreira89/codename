package com.stevebarreira.codename.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class WordList {
	
	private static int defaultSize = 25;
	private Random random = new Random();
	private List<String> wordList;

	public WordList(List<String> wordList) {
		this.wordList = wordList;
	}

	public List<String> getWordList() {
		return wordList;
	}

	public void setWordList(List<String> wordList) {
		this.wordList = wordList;
	}

	public WordList(){
		this.wordList = createWordMap();
	}
	
	@JsonIgnore
	public String getRandomWord(){
		int wordListSize = wordList.size();
		int randomValue = wordListSize <= 0 ? 0 : random.nextInt(wordListSize);
		String output = wordList.get(randomValue);
		wordList.remove(randomValue);
		return output;
	}
	
	private List<String> createWordMap() {
		List<String> wordList = new ArrayList<String>();
		wordList.add("PLASTIC");
		wordList.add("DWARF");
		wordList.add("SMUGGLER");
		wordList.add("ENGLAND");
		wordList.add("CLUB");
		wordList.add("DRILL");
		wordList.add("BAND");
		wordList.add("SINK");
		wordList.add("FIRE");
		wordList.add("BERMUDA");
		wordList.add("JAM");
		wordList.add("ARM");
		wordList.add("ORGAN");
		wordList.add("PIRATE");
		wordList.add("LAB");
		wordList.add("HORSE");
		wordList.add("TRIP");
		wordList.add("SQUARE");
		wordList.add("EYE");
		wordList.add("BOARD");
		wordList.add("TABLE");
		wordList.add("DOCTOR");
		wordList.add("BEACH");
		wordList.add("ROCK");
		wordList.add("SPIDER");
		wordList.add("FOREST");
		wordList.add("KNIFE");
		return wordList;
	}
}

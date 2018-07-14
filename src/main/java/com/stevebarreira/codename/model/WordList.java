package com.stevebarreira.codename.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordList {

    private final static String EMPTY_WORDLIST_VALUE = "EMPTYLIST";
    private static int DEFAULT_LIST_SIZE = 25;
    private Random random = new Random();
    private List<String> wordList;
    private List<String> selectWordList;

    public WordList(List<String> wordList) {
        this.wordList = wordList;
        this.selectWordList = new ArrayList<>(wordList);
    }

    public WordList() {
        this.wordList = createDefaultWordList();
        this.selectWordList = new ArrayList<>(wordList);
    }

    public List<String> getWordList() {
        return wordList;
    }

    public void setWordList(List<String> wordList) {
        this.wordList = wordList;
    }

    @JsonIgnore
    public String getRandomWord() {
        int wordListSize = selectWordList.size();
        if (wordListSize == 0) {
            return EMPTY_WORDLIST_VALUE;
        }
        int randomValue = wordListSize == 1 ? 0 : random.nextInt(wordListSize);
        String output = selectWordList.get(randomValue);
        selectWordList.remove(randomValue);
        return output;
    }

    @JsonIgnore
    public List<String> createDefaultWordList() {
        List<String> wordList = new ArrayList<>();
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
        return wordList;
    }
}

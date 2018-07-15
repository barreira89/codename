package com.stevebarreira.codename.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WordList {

    private List<Word> words;

    private List<Word> transformStringToWords(List<String> wordString) {
        return Optional.ofNullable(wordString)
                .orElse(Collections.emptyList())
                .stream()
                .map(Word::new)
                .collect(Collectors.toList());
    }

    private List<String> transformWordsToString(List<Word> words) {
        return Optional.ofNullable(words)
                .orElse(Collections.emptyList())
                .stream()
                .map(Word::getValue)
                .collect(Collectors.toList());
    }

    public WordList(List<String> words) {
        this.words = transformStringToWords(words);
    }

    public WordList() {
        this.words = createDefaultWordList();
    }

    public List<Word> getWords(){return this.words;}

    public List<String> getStringWords() {
        return words.stream().map(Word::getValue).collect(Collectors.toList());
    }

    public void setWords(List<String> words) {
        this.words = transformStringToWords(words);
    }

    @JsonIgnore
    private List<Word> createDefaultWordList() {
        return Arrays.asList(
                new Word("PLASTIC"),
                new Word("DWARF"),
                new Word("SMUGGLER"),
                new Word("ENGLAND"),
                new Word("CLUB"),
                new Word("DRILL"),
                new Word("BAND"),
                new Word("SINK"),
                new Word("FIRE"),
                new Word("BERMUDA"),
                new Word("JAM"),
                new Word("ARM"),
                new Word("ORGAN"),
                new Word("PIRATE"),
                new Word("LAB"),
                new Word("HORSE"),
                new Word("TRIP"),
                new Word("SQUARE"),
                new Word("EYE"),
                new Word("BOARD"),
                new Word("TABLE"),
                new Word("DOCTOR"),
                new Word("BEACH"),
                new Word("ROCK"),
                new Word("SPIDER")
        );
    }
}

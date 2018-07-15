package com.stevebarreira.codename.service.impl;

import com.stevebarreira.codename.exception.GameBoardCreationException;
import com.stevebarreira.codename.model.Word;
import com.stevebarreira.codename.model.WordList;
import com.stevebarreira.codename.model.dto.Codenames;
import com.stevebarreira.codename.repository.WordlistRepository;
import com.stevebarreira.codename.service.WordListService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WordListServiceImpl implements WordListService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WordListServiceImpl.class);

    private static final int DEFAULTSIZE = 25;

    @Autowired
    private Random generator;

    @Autowired
    private WordlistRepository wordListRepository;

    @Override
    public WordList getRandomWordList() {
        List<String> words = getWordsFromRepo();
        return new WordList(randomizedWordList(words, DEFAULTSIZE));
    }

    @Override
    public Word getRandomWord(WordList wordList) {
        Collections.shuffle(wordList.getWords());
        return wordList.getWords()
                .stream()
                .filter(word -> !word.isSelected())
                .findFirst()
                .map(Word::selectWord)
                .orElseThrow(() -> new GameBoardCreationException("OUT OF WORDS"));
    }

    private List<String> getWordsFromRepo() {
        Codenames codeNameList;
        List<Codenames> wordList = wordListRepository.findAll();

        if (CollectionUtils.isNotEmpty(wordList)) {
            int codeNameListIndex = randomValueFromWordList(wordList);
            codeNameList = wordList.get(codeNameListIndex);
        } else {
            LOGGER.info("Repository Unavaible | Using Default Codewords List");
            codeNameList = getDefaultCodeNames();
        }
        return codeNameList.getWords();
    }

    private Codenames getDefaultCodeNames() {
        List<Word> defaultWords = createDefaultWordList();
        Collections.shuffle(defaultWords);
        return new Codenames(defaultWords);
    }

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

    private int randomValueFromWordList(List wordList) {
        return generator.nextInt(wordList.size());
    }

    private List<Word> randomizedWordList(List<String> inputList, Integer sizeOfOutput) {
        int size = inputList.size();
        List<Word> inputWords = inputList.stream().map(Word::new).collect(Collectors.toList());
        List<Word> randomWordList = new ArrayList<>();

        for (int i = 0; i < sizeOfOutput; i++) {
            int randomSelection = generator.nextInt(size);
            randomWordList.add(inputWords.get(randomSelection));
        }

        return randomWordList;
    }
}

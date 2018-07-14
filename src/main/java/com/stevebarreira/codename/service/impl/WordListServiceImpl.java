package com.stevebarreira.codename.service.impl;

import com.stevebarreira.codename.model.WordList;
import com.stevebarreira.codename.model.dto.Codenames;
import com.stevebarreira.codename.repository.WordlistRepository;
import com.stevebarreira.codename.service.WordListService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
		List<String> wordList = getWordListFromRepo();
		return new WordList(randomizedWordList(wordList, DEFAULTSIZE));
	}

	private List<String> getWordListFromRepo() {
		Codenames codeNameList;
		List<Codenames> wordList = wordListRepository.findAll();
		
		if(CollectionUtils.isNotEmpty(wordList)){
			int codeNameListIndex = randomValueFromWordList(wordList);
			codeNameList = wordList.get(codeNameListIndex);
		} else {
			LOGGER.info("Repository Unavaible | Using Default Codewords List");
			codeNameList = getDefaultCodeNames();
		}
		return codeNameList.getWords();
	}

	private Codenames getDefaultCodeNames() {
		Codenames codeNameList = new Codenames();
		codeNameList.setWords(new WordList().createDefaultWordList());
		return codeNameList;
	}

	private int randomValueFromWordList(List wordList){
		return generator.nextInt(wordList.size());
	}

	private List<String> randomizedWordList(List<String> inputList, Integer sizeOfOutput) {
		List<String> randomWordList = new ArrayList<>();

		int size = inputList.size();
		if (size < sizeOfOutput) {
			throw new IllegalArgumentException("Input List must be greater than output list");
		}

		for (int i = 0; i < sizeOfOutput; i++) {
			int randomSelection = generator.nextInt(size);
			randomWordList.add(inputList.get(randomSelection).toUpperCase());
		}

		return randomWordList;
	}
}

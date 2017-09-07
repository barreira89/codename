package com.stevebarreira.codename.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import javax.validation.constraints.AssertTrue;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stevebarreira.codename.controller.SocketController;
import com.stevebarreira.codename.model.WordList;
import com.stevebarreira.codename.model.dto.Codenames;
import com.stevebarreira.codename.repository.WordlistRepository;
import com.stevebarreira.codename.service.WordListService;

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

	@Override
	public List<String> getRandomWordList(Integer size) {
		List<String> wordList = getWordListFromRepo();
		try {
			return randomizedWordList(wordList, size);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<String> getWordListFromRepo() {
		Codenames codeNameList;
		List<Codenames> wordList = wordListRepository.findAll();
		
		if(CollectionUtils.isNotEmpty(wordList)){
			codeNameList = wordList.get(0);
		} else {
			LOGGER.info("Repository Unavaible | Using Default Codewords List");
			codeNameList = getDefaultCodeNames();
		}
		return codeNameList.getWords();
	}

	private Codenames getDefaultCodeNames() {
		Codenames codeNameList = new Codenames();
		codeNameList.setWords(new WordList().createWordMap());
		return codeNameList;
	}

	private List<String> randomizedWordList(List<String> inputList, Integer sizeOfOutput) {
		List<String> randomWordList = new ArrayList<String>();

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

	@Override
	public List<String> getRandomWordListString() {
		// TODO Auto-generated method stub
		return null;
	}

}

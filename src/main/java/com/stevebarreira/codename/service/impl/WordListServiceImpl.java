package com.stevebarreira.codename.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stevebarreira.codename.model.WordList;
import com.stevebarreira.codename.model.dto.Codenames;
import com.stevebarreira.codename.repository.WordlistRepository;
import com.stevebarreira.codename.service.WordListService;

@Service
public class WordListServiceImpl implements WordListService {

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
		return randomizedWordList(wordList, size);
	}
	
	private List<String> getWordListFromRepo(){
		Codenames codeNamelist =  wordListRepository.findAll().get(0);
		return codeNamelist.getWords();
	}
	
	private List<String> randomizedWordList (List<String> inputList, Integer sizeOfOutput){
		Set<String> randomWordList = new HashSet<String>();
		
		int size = inputList.size();
		for (int i = 0; i < sizeOfOutput; i ++){
			int randomSelection = generator.nextInt(size);
			randomWordList.add(inputList.get(randomSelection).toUpperCase());
		}
		
		return new ArrayList<String>(randomWordList);
	}

	@Override
	public List<String> getRandomWordListString() {
		// TODO Auto-generated method stub
		return null;
	}
	


}

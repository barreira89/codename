package com.stevebarreira.codename.model

import spock.lang.Specification

class WordListSpec extends Specification {
	
	def "Word List Size"(){
		given:
		def wordList = new WordList();
		
		expect:
		wordList.getWordList().size() == 25;
	}
	
	def "Get Random Word"(){
		given:
			def wordList = new WordList()
			wordList.getRandomWord();
			
		expect:
			wordList.getWordList().size() == 25;
			wordList.selectWordList.size() == wordList.getWordList().size() - 1
		
	}
	
	def "Use up Select List"(){
		given:
			def wordList = new WordList()
			25.times{
				wordList.getRandomWord();
			}
		expect:
			wordList.getWordList().size() == 25;
			wordList.selectWordList.size() == 0;
		
	}
	
	def "Over use Select List"(){
		given:
			def wordList = new WordList()
			26.times{
				wordList.getRandomWord();
			}
		expect:
			wordList.getWordList().size() == 25;
			wordList.selectWordList.size() == 0;
		
	}
	
	def "Empty List"(){
		given:
			def emptyList = new ArrayList<String>()
			def wordList = new WordList(emptyList)
			def emptyValue = wordList.getRandomWord();
			
		expect:
			wordList.getWordList().size() == 0;
			wordList.selectWordList.size() == 0;
			emptyValue == "EMPTYLIST"
		
	}

}

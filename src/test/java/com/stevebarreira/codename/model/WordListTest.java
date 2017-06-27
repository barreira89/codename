package com.stevebarreira.codename.model;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WordList.class})
public class WordListTest {
	
	@Test
	public void emptyWordList(){
		WordList wordList = new WordList();
		String randomWord = wordList.getRandomWord();
		assertNotEquals(randomWord, null);
		
	}

}

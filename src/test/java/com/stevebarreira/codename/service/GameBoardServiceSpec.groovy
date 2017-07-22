package com.stevebarreira.codename.service

import com.stevebarreira.codename.model.GameBoards
import com.stevebarreira.codename.model.WordList
import com.stevebarreira.codename.repository.GameBoardRepository
import com.stevebarreira.codename.service.impl.GameBoardServiceImpl

class GameBoardServiceSpec extends GameSpec {
	
	WordListService mockWordListService = Mock()
	GameBoardRepository mockGameBoardRepository = Mock()
	GameBoardService gameBoardService = new GameBoardServiceImpl(
												wordListService: mockWordListService,
												gameBoardRepository: mockGameBoardRepository
											)
	
	
	
	
	def 'createRandomGameBoard - valid'(){
		setup:
		WordList randomWordList = new WordList(wordList: wordListOf25())
		GameBoards gameBoardRepoResult = new GameBoards(
				wordList: randomWordList
			)
		
		when:
		GameBoards gameBoardResult = gameBoardService.createRandomGameBoard()
		
		then:
		1 * mockWordListService.getRandomWordList() >> randomWordList
		1 * mockGameBoardRepository.save(_ as GameBoards) >> gameBoardRepoResult
		0 * _
		
		and:
		gameBoardResult
		gameBoardResult.wordList
		gameBoardResult.wordList.wordList.size == 25
		
	}

}

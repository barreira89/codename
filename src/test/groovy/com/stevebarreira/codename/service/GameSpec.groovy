package com.stevebarreira.codename.service

import com.stevebarreira.codename.model.GameBoards
import com.stevebarreira.codename.model.GameClue
import com.stevebarreira.codename.model.GameRound
import com.stevebarreira.codename.model.GameRow
import com.stevebarreira.codename.model.Games
import com.stevebarreira.codename.model.Team
import com.stevebarreira.codename.model.WordList

import java.util.List

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import spock.lang.Shared
import spock.lang.Specification

class GameSpec extends Specification {
	
	/*
	 * Games objects used for testing
	 */
	
	@Shared
	Games emptyGame = new Games(
		rounds: [new GameRound()]
	)
	
	@Shared
	List<GameClue> defaultGameClues = [
		new GameClue(
			numberOfWords: 2,
			clue: 'TEST CLUE',
			team: 'RED'
		),
		new GameClue(
			numberOfWords: 2,
			clue: 'TEST CLUE',
			team: 'RED'
		)
	]
	
	@Shared
	Games defaultGame = new Games(
		rounds: [
			new GameRound(
				id: '123123123',
				roundNumber: 1,
				gameClues: defaultGameClues,
				gameBoard: defaultGameBoard
				),
			new GameRound(
				id: '99',
				roundNumber: 3
				)
			
		]
	)
	
	
	
	def wordListOf25(){
		def array = new ArrayList<String>()
		25.times{
			array << "TEST"
		}
		
		return array
	}
	
	@Shared
	List<Games> listOf4Games = [defaultGame, defaultGame, defaultGame, defaultGame]
	
	def gameListOf4() {
		def array = new ArrayList<Games>()
		4.times{
			array << defaultGame
		}
		return array
	}
	
	@Shared
	def defaultWordList = new WordList(
				wordList: ["TEST", "TEST1"]
		)
		
	@Shared
	GameBoards defaultGameBoard = new GameBoards(
			leadTeam: Team.RED.getValueOf(),
			gameRows: [new GameRow(),new GameRow()],
			wordList: defaultWordList
		)
	
	
	
	@Shared
	Page<Games> defaultPageGames = new PageImpl<Games>([defaultGame], new PageRequest(0, 1), 1)
	
	@Shared
	Page<Games> listOf4PageGames = new PageImpl<Games>(listOf4Games, new PageRequest(0, 1), 4)
	
	
}

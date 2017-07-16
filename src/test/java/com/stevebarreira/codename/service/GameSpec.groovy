package com.stevebarreira.codename.service

import com.stevebarreira.codename.model.GameClue
import com.stevebarreira.codename.model.GameRound
import com.stevebarreira.codename.model.Games
import spock.lang.Shared
import spock.lang.Specification

class GameSpec extends Specification {
	
	@Shared
	def emptyGame = new Games(
		rounds: [new GameRound()]
	)
	
	@Shared
	def defaultGame = new Games(
		rounds: [
			new GameRound(
				id: '123123123',
				roundNumber: 1,
				gameClues: getGameClues()
				)	
		]	
	)
	
	def gameClues = [
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

}

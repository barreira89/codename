package com.stevebarreira.codename.service

import com.stevebarreira.codename.model.GameBoards
import com.stevebarreira.codename.model.WordList
import com.stevebarreira.codename.repository.GameBoardRepository
import com.stevebarreira.codename.service.impl.GameBoardServiceImpl
import spock.lang.Specification

class GameBoardServiceSpec extends Specification implements GameSpec {

    WordListService mockWordListService = Mock()
    GameBoardRepository mockGameBoardRepository = Mock()
    GameBoardService gameBoardService = new GameBoardServiceImpl(
            wordListService: mockWordListService,
            gameBoardRepository: mockGameBoardRepository
    )


    def 'createRandomGameBoard - valid'() {
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

    def 'createRandomGameBoard - no result from wordList Repo'() {
        when:
        GameBoards gameBoardResult = gameBoardService.createRandomGameBoard()

        then:
        1 * mockWordListService.getRandomWordList() >> null
        0 * _

        and:
        gameBoardResult

    }

    def 'getAllGameBoards - result from Repo'() {
        setup:
        List<GameBoards> gameBoardsFromRepo = new ArrayList<GameBoards>()
        gameBoardsFromRepo << defaultGameBoard

        when:
        List<GameBoards> gameBoardResult = gameBoardService.getAllGameBoards()


        then:
        1 * mockGameBoardRepository.findAll() >> [gameBoardsFromRepo]
        0 * _

        and:
        gameBoardResult
        gameBoardResult == [gameBoardsFromRepo]
    }

    def 'getGameBoardById - result from Repo'() {
        setup:
        GameBoards gameBoardFromRepo = new GameBoards()

        when:
        GameBoards gameBoardResult = gameBoardService.getGameBoardById("1")

        then:
        1 * mockGameBoardRepository.findOne(_) >> gameBoardFromRepo
        0 * _

        and:
        gameBoardResult == gameBoardFromRepo

    }

    def 'updateGameBoard - empty Value Passed'() {
        setup:
        GameBoards gameBoardToUpdate = new GameBoards()

        when:
        GameBoards gameBoardResult = gameBoardService.updateGameBoard(null)

        then:
        1 * mockGameBoardRepository.save(_)
        0 * _

        and:
        gameBoardResult == null
    }

    def 'updateGameBoard - value passed'() {
        setup:
        GameBoards gameBoardToUpdate = new GameBoards()

        when:
        GameBoards gameBoardResult = gameBoardService.updateGameBoard(gameBoardToUpdate)

        then:
        1 * mockGameBoardRepository.save(gameBoardToUpdate) >> gameBoardToUpdate
        0 * _

        and:
        gameBoardResult == gameBoardToUpdate
    }

}

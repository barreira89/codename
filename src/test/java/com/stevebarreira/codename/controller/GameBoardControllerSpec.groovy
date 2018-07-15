package com.stevebarreira.codename.controller

import com.fasterxml.jackson.core.type.TypeReference
import com.stevebarreira.codename.model.GameBoards
import com.stevebarreira.codename.model.WordList
import com.stevebarreira.codename.service.GameBoardService
import com.stevebarreira.codename.service.WordListService
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put

class GameBoardControllerSpec extends Specification implements ControllerTrait {

    GameBoardService mockGameBoardService = Mock()
    WordListService mockWordListService = Mock()

    GameBoardController gameBoardController = new GameBoardController(
            gameBoardService: mockGameBoardService,
            wordListService: mockWordListService
    )
    MockMvc mockMvc = getDefaultMockMvc(gameBoardController)

    String API_BASE = "/api/gameboards"

    def 'POST - GameBoards - Create Game Board'() {
        setup:
        GameBoards gameBoards = new GameBoards()

        when:
        def response = mockMvc.perform(post(API_BASE)
                .contentType(APPLICATION_JSON)
        ).andReturn().response
        GameBoards gameBoardResponse = objectMapper.readValue(response.contentAsString, GameBoards.class)

        then:
        1 * mockGameBoardService.createRandomGameBoard() >> gameBoards
        0 * _

        and:
        response.status == 200
        gameBoardResponse

    }

    def 'POST - GameBoards - Create Game Board - Exception'() {
        when:
        def response = mockMvc.perform(post(API_BASE)
                .contentType(APPLICATION_JSON)
        ).andReturn().response

        then:
        1 * mockGameBoardService.createRandomGameBoard() >> { throw new RuntimeException() }
        0 * _

        and:
        response
        response.status == 500

    }

    def 'GET - GameBoards - Get Game Board by ID'() {
        setup:
        String gameBoardId = "123"

        when:
        def response = mockMvc.perform(get(API_BASE + "/" + gameBoardId).contentType(APPLICATION_JSON)).andReturn().response
        GameBoards gameBoardsResponse = objectMapper.readValue(response.contentAsString, GameBoards.class)

        then:
        1 * mockGameBoardService.getGameBoardById(gameBoardId) >> new GameBoards(id: gameBoardId)
        0 * _

        and:
        gameBoardsResponse
        gameBoardsResponse.id == gameBoardId
    }

    def 'GET - GameBoards - Get all Gameboards'() {
        when:
        def response = mockMvc.perform(get(API_BASE).contentType(APPLICATION_JSON)).andReturn().response
        List<GameBoards> gameBoardsResponse = objectMapper.readValue(response.contentAsString, new TypeReference<List<GameBoards>>(){})

        then:
        1 * mockGameBoardService.getAllGameBoards() >> [new GameBoards(), new GameBoards()]

        and:
        gameBoardsResponse.size() == 2
    }

    def 'PUT - GameBoards - Update GameBoard by ID'() {
        given:
        String gameBoardId = "123458-abdCk"
        GameBoards gameBoard = new GameBoards(
                id: gameBoardId
        )
        GameBoards gb

        when:
        def response = mockMvc.perform(put(API_BASE + "/" + gameBoardId)
                .content(objectMapper.writeValueAsString(gameBoard))
                .contentType(APPLICATION_JSON))
                .andReturn().response

        def gameBoardResponse = objectMapper.readValue(response.contentAsString, GameBoards.class)

        then:
        1 * mockGameBoardService.updateGameBoard({gb = it}) >> gameBoard
        0 * _

        and:
        response
        response.status == 200

        and:
        gameBoardResponse.id == gameBoardId

        and:
        gb

    }

    def 'GET - Words - Get All Words'() {
        when:
        def response = mockMvc.perform(get("/api/wordlist").contentType(APPLICATION_JSON)).andReturn().response
        WordList wordListResponse = objectMapper.readValue(response.contentAsString, WordList.class)

        then:
        1 * mockWordListService.getRandomWordList() >> new WordList()

        and:
        response
        response.status == 200

        and:
        wordListResponse
        wordListResponse.stringWords
    }
}

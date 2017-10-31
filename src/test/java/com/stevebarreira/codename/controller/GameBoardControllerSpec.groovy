package com.stevebarreira.codename.controller

import com.stevebarreira.codename.model.GameBoards
import com.stevebarreira.codename.service.GameBoardService
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

class GameBoardControllerSpec extends Specification implements ControllerTrait {

    GameBoardService mockGameBoardService = Mock()

    GameBoardController gameBoardController = new GameBoardController(
            gameBoardService: mockGameBoardService
    )
    MockMvc mockMvc = getDefaultMockMvc(gameBoardController)

    static String apiBase = "/api/gameboards"

    def 'POST - GameBoards - Create Game Board'() {
        setup:
        GameBoards gameBoards = new GameBoards()

        when:
        def response = mockMvc.perform(
                post(apiBase)
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
        def response = mockMvc.perform(
                post(apiBase)
                .contentType(APPLICATION_JSON)
        ).andReturn().response

        then:
        1 * mockGameBoardService.createRandomGameBoard() >> { throw new RuntimeException() }
        0 * _

        and:
        response
        response.status == 500

    }

    def 'GET - GameBoards - Get Game Board by ID'(){
        setup:
        String gameBoardId = "123"

        when:
        def response = mockMvc.perform(get(apiBase + "/" + gameBoardId).contentType(APPLICATION_JSON)).andReturn().response
        GameBoards gameBoardsResponse = objectMapper.readValue(response.contentAsString, GameBoards.class)

        then:
        1 * mockGameBoardService.getGameBoardById(gameBoardId) >> new GameBoards(id: gameBoardId)
        0 * _

        and:
        gameBoardsResponse
        gameBoardsResponse.id == gameBoardId
    }

    def 'GET - GameBoards - Get All GameBoards'(){

    }

}

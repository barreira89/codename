package com.stevebarreira.codename.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.stevebarreira.codename.model.GameBoards
import com.stevebarreira.codename.service.GameBoardService
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver
import spock.lang.Specification

import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

class GameBoardControllerSpec extends Specification implements ControllerTrait {

    GameBoardService mockGameBoardService = Mock()

    GameBoardController gameBoardController = new GameBoardController(
            gameBoardService: mockGameBoardService
    )

    ObjectMapper objectMapper = new ObjectMapper()
    MockMvc mockMvc = MockMvcBuilders
            .standaloneSetup(gameBoardController)
            .setHandlerExceptionResolvers(exceptionResolver)
            .build()

    def 'POST - Games - Create Game Board'() {
        setup:
        GameBoards gameBoards = new GameBoards()

        when:
        def response = mockMvc.perform(post("/api/gameboards")
                .contentType(APPLICATION_JSON)
        ).andReturn().response
        GameBoards gameBoardResponse = objectMapper.readValue(response.contentAsString, GameBoards.class)

        then:
        1 * mockGameBoardService.createRandomGameBoard() >> gameBoards

        and:
        response.status == 200
        gameBoardResponse

    }


    def 'POST - Games - Create Game Board - Exception'() {
        when:
        def response = mockMvc.perform(post("/api/gameboards")
                .contentType(APPLICATION_JSON)
        ).andReturn().response

        then:
        1 * mockGameBoardService.createRandomGameBoard() >> { throw new RuntimeException() }

        and:
        response
        response.status == 500

    }

}

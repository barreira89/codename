package com.stevebarreira.codename.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.stevebarreira.codename.model.Games
import com.stevebarreira.codename.service.GameService
import com.stevebarreira.codename.service.WordListService
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

class GameControllerSpec extends Specification {

    GameService mockGameService = Mock()

    GameController gameController = new GameController(
            gamesService: mockGameService
    )
    ObjectMapper objectMapper = new ObjectMapper()
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(gameController).build()

    def 'POST - Games - Create Games'() {
        setup:
        Games gameToReturn = new Games()

        when:
        def response = mockMvc.perform(post("/api/games")
                .contentType(APPLICATION_JSON)
        ).andReturn().response
        Games gameResponse = objectMapper.readValue(response.contentAsString, Games.class)

        then:
        1 * mockGameService.createNewGame() >> gameToReturn

        and:
        response.status == 200
        gameResponse

    }


}

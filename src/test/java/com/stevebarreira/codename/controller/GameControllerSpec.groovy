package com.stevebarreira.codename.controller

import com.stevebarreira.codename.model.Games
import com.stevebarreira.codename.service.GameService
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

class GameControllerSpec extends Specification implements ControllerTrait {

    GameService mockGameService = Mock()

    GameController gameController = new GameController(
            gamesService: mockGameService
    )

    MockMvc mockMvc = getDefaultMockMvc(gameController)
    static String apiBase = "/api/games"

    def 'POST - Games - Create Games'() {
        setup:
        Games gameToReturn = new Games()

        when:
        def response = mockMvc.perform(
                post(apiBase)
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

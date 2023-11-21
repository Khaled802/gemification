package com.example.gemification.game.controller;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.gemification.game.BadgeCard;
import com.example.gemification.game.DTO.AttemptDTO;
import com.example.gemification.game.DTO.GameResultDTO;
import com.example.gemification.game.enums.BadgeType;
import com.example.gemification.game.service.GameService;

@WebMvcTest(controllers = GameController.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureJsonTesters
public class GameControllerTest {
    @MockBean
    private GameService gameService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<AttemptDTO> jsonRequest;

    @Autowired
    private JacksonTester<GameResultDTO> jsonResponse;

    @Test
    void testNewAttempt() throws IOException, Exception {
        AttemptDTO attemptDTO = new AttemptDTO(1L, "Mohamed", 10L, 11, 20, true);
        given(gameService.addNewAttempt(attemptDTO))
                .willReturn(new GameResultDTO(10, List.of(new BadgeCard(10L, BadgeType.FIRST_CHALLENGE))));

        var response = mvc.perform(post("/attempts").contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest.write(attemptDTO).getJson())).andReturn().getResponse();

        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        var result = jsonResponse.parse(response.getContentAsString()).getObject();
        then(result.getBadges()).isEqualTo(List.of(new BadgeCard(10L, BadgeType.FIRST_CHALLENGE)));
        then(result.getTotalScore()).isEqualTo(10);
    }

}

package com.example.gemification.game.controller;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.example.gemification.game.LeaderBoard;
import com.example.gemification.game.enums.BadgeType;
import com.example.gemification.game.service.LeaderBoardService;

@WebMvcTest(controllers = LeaderBoardController.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureJsonTesters
public class LeaderBoardControllerTest {
    @MockBean
    private LeaderBoardService leaderBoardService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<List<LeaderBoard>> jsonResponse;

    @Test
    void testGetLeaderBoard() throws Exception {
        List<LeaderBoard> leaderBoards = List.of(
                new LeaderBoard(10L, 50,
                        List.of(BadgeType.FIRST_CHALLENGE.getDescription(), BadgeType.BRONZE.getDescription())),
                new LeaderBoard(10L, 40,
                        List.of(BadgeType.FIRST_CHALLENGE.getDescription())),
                new LeaderBoard(10L, 30,
                        List.of(BadgeType.FIRST_CHALLENGE.getDescription())),
                new LeaderBoard(10L, 20,
                        List.of(BadgeType.FIRST_CHALLENGE.getDescription())));
        given(leaderBoardService.getLeaderBoard()).willReturn(leaderBoards);

        MockHttpServletResponse response = mvc.perform(get("/leaderboard").contentType(MediaType.APPLICATION_JSON)
                ).andReturn().getResponse();
        
        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString()).isEqualTo(jsonResponse.write(leaderBoards).getJson());
    }
}

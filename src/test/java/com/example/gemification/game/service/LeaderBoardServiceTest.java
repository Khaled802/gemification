package com.example.gemification.game.service;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.gemification.game.BadgeCard;
import com.example.gemification.game.LeaderBoard;
import com.example.gemification.game.enums.BadgeType;
import com.example.gemification.game.repository.BadgeCardRepository;
import com.example.gemification.game.repository.ScoreCardRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class LeaderBoardServiceTest {
    @Autowired
    LeaderBoardService leaderBoardService;

    @MockBean
    ScoreCardRepository scoreCardRepository;

    @MockBean
    BadgeCardRepository badgeCardRepository;

    @Test
    void testGetLeaderBoard() {
        Map<Long, List<BadgeCard>> badges = Map.of(
                10L, List.of(new BadgeCard(10L, BadgeType.BRONZE), new BadgeCard(10L, BadgeType.FIRST_CHALLENGE)),
                5L, List.of(new BadgeCard(5L, BadgeType.FIRST_CHALLENGE)),
                1L, List.of(new BadgeCard(5L, BadgeType.FIRST_CHALLENGE)),
                2L, List.of(new BadgeCard(5L, BadgeType.FIRST_CHALLENGE)));
        given(scoreCardRepository.getLeadingBoard(4)).willReturn(List.of(
            new LeaderBoard(10L, 50l),            
            new LeaderBoard(5L, 40l),
            new LeaderBoard(1L, 30l),
            new LeaderBoard(2L, 20l)
        ));
        given(badgeCardRepository.findByUserId(10L)).willReturn(badges.get(10L));
        given(badgeCardRepository.findByUserId(5L)).willReturn(badges.get(5L));
        given(badgeCardRepository.findByUserId(1L)).willReturn(badges.get(1L));
        given(badgeCardRepository.findByUserId(2L)).willReturn(badges.get(2L));

        List<LeaderBoard> leaderBoard = leaderBoardService.getLeaderBoard();
        then(leaderBoard.size()).isEqualTo(badges.size());

        for (LeaderBoard elementBoard : leaderBoard) {
            then(badges.containsKey(elementBoard.getUserId())).isTrue();
            List<String> result = elementBoard.getBadges().stream().toList();
            List<String> expected = badges.get(elementBoard.getUserId()).stream()
                    .map((ele) -> ele.getBadgeType().getDescription()).sorted().toList();
            then(result).isEqualTo(expected);

            // then(String.join("&", ;)).isEqualTo(String.join("&", null));

        }

    }
}

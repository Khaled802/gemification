package com.example.gemification.game.service;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.gemification.game.BadgeCard;
import com.example.gemification.game.ScoreCard;
import com.example.gemification.game.DTO.AttemptDTO;
import com.example.gemification.game.DTO.GameResultDTO;
import com.example.gemification.game.enums.BadgeType;
import com.example.gemification.game.repository.BadgeCardRepository;
import com.example.gemification.game.repository.ScoreCardRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class GameServiceImpTest {
    @Autowired
    private GameService gameService;

    @MockBean
    private ScoreCardRepository scoreCardRepository;

    @MockBean
    private BadgeCardRepository badgeCardRepository;

    @Test
    void testAddNewAttempt() {
        ScoreCard scoreCard = new ScoreCard(1L, 10L);
        List<BadgeCard> badgeCards = List.of(new BadgeCard(1L, BadgeType.FIRST_CHALLENGE));
        
        given(scoreCardRepository.findByUserId(1L)).willReturn(List.of(scoreCard));        
        given(scoreCardRepository.getTotalScore(1L)).willReturn(ScoreCard.DEFAULT_SCORE);

        given(badgeCardRepository.findByUserId(1L)).willReturn(List.of());
        
        given(scoreCardRepository.save(scoreCard)).willReturn(scoreCard);
        given(badgeCardRepository.saveAll(badgeCards)).willReturn(badgeCards);

        AttemptDTO attemptDTO = new AttemptDTO(10L, "Mohamed", 1L, 20, 11, true);
        GameResultDTO gameResultDTO = gameService.addNewAttempt(attemptDTO);

        then(gameResultDTO.getTotalScore()).isEqualTo(ScoreCard.DEFAULT_SCORE);
        List<BadgeCard> badgeCardsResult = gameResultDTO.getBadges();
        then(badgeCardsResult.size()).isEqualTo(1);
        then(badgeCardsResult.get(0).getBadgeType()).isEqualTo(BadgeType.FIRST_CHALLENGE);        
        then(badgeCardsResult.get(0).getUserId()).isEqualTo(1L);

    }


    @Test
    void testAddNewAttemptWithWrong() {      
        given(scoreCardRepository.findByUserId(1L)).willReturn(List.of());
        given(badgeCardRepository.findByUserId(1L)).willReturn(List.of());
        
        // given(scoreCardRepository.save(scoreCard)).willReturn(scoreCard);
        given(badgeCardRepository.saveAll(List.of())).willReturn(List.of());
        given(scoreCardRepository.getTotalScore(1L)).willReturn(ScoreCard.DEFAULT_SCORE);

        AttemptDTO attemptDTO = new AttemptDTO(10L, "Mohamed", 1L, 20, 11, false);
        GameResultDTO gameResultDTO = gameService.addNewAttempt(attemptDTO);

        then(gameResultDTO.getTotalScore()).isEqualTo(0);
        List<BadgeCard> badgeCardsResult = gameResultDTO.getBadges();
        then(badgeCardsResult.size()).isEqualTo(0);

    }
}
package com.example.gemification.game.badgeProcessors;

import static org.assertj.core.api.BDDAssertions.then;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.gemification.game.ScoreCard;
import com.example.gemification.game.DTO.AttemptDTO;
import com.example.gemification.game.enums.BadgeType;

@SpringBootTest
public class BronzeBadgeProcessorTest {
    
    @Autowired
    private BronzeBadgeProcessor bronzeBadgeProcessor;

    @Test
    void testGetBadgeOrNullWithEqualToBadge() {
        AttemptDTO attemptDTO = new AttemptDTO(10L, "Mohamed", 1L, 11, 20, true);
        List<ScoreCard> scoreCards = List.of(
            new ScoreCard(1L, 10L),
            new ScoreCard(1L, 6L),            
            new ScoreCard(1L, 5L),            
            new ScoreCard(1L, 2L),            
            new ScoreCard(1L, 1L)         
        );

        Optional<BadgeType> badge = bronzeBadgeProcessor.getBadgeOrNull(attemptDTO, 50, scoreCards);
        
        then(badge.get()).isEqualTo(BadgeType.BRONZE);
    }


    
    @Test
    void testGetBadgeOrNullWithLessThanBadge() {
        AttemptDTO attemptDTO = new AttemptDTO(5L, "Mohamed", 1L, 11, 20, true);
        List<ScoreCard> scoreCards = List.of(           
            new ScoreCard(1L, 5L),            
            new ScoreCard(1L, 2L),            
            new ScoreCard(1L, 1L)         
        );

        Optional<BadgeType> badge = bronzeBadgeProcessor.getBadgeOrNull(attemptDTO, 30, scoreCards);
        
        then(badge.isPresent()).isEqualTo(false);
    }


    @Test
    void testGetBadgeOrNullWithGreaterThanBadge() {
        AttemptDTO attemptDTO = new AttemptDTO(10L, "Mohamed", 1L, 11, 20, true);
        List<ScoreCard> scoreCards = List.of(
            new ScoreCard(1L, 10L),
            new ScoreCard(1L, 6L),            
            new ScoreCard(1L, 5L), 
            new ScoreCard(1L, 3L),
            new ScoreCard(1L, 2L),            
            new ScoreCard(1L, 1L)         
        );

        Optional<BadgeType> badge = bronzeBadgeProcessor.getBadgeOrNull(attemptDTO, 60, scoreCards);
        
        then(badge.get()).isEqualTo(BadgeType.BRONZE);
    }
    

    @Test
    void testGetBadgeType() {
        then(bronzeBadgeProcessor.getBadgeType()).isEqualTo(BadgeType.BRONZE);
    }
}

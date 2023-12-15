package com.example.gemification.game.badgeProcessors;

import static org.assertj.core.api.BDDAssertions.then;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.gemification.game.ScoreCard;
import com.example.gemification.game.DTO.AttemptSolvedEvent;
import com.example.gemification.game.enums.BadgeType;

@SpringBootTest
public class FirstChallengeBadgeProcessorTest {
    
    @Autowired
    private FirstChallengeBadgeProcessor firstChallengeBadgeProcessor;

    @Test
    void testGetBadgeOrNullWithFirstChallenge() {
        AttemptSolvedEvent attemptDTO = new AttemptSolvedEvent(10L, "Mohamed", 1L, 11, 20, true);
        List<ScoreCard> scoreCards = List.of(          
            new ScoreCard(1L, 1L)         
        );

        Optional<BadgeType> badge = firstChallengeBadgeProcessor.getBadgeOrNull(attemptDTO, 10, scoreCards);
        
        then(badge.get()).isEqualTo(BadgeType.FIRST_CHALLENGE);
    }


    
    @Test
    void testGetBadgeOrNullWithWrongAttemptAndNoScore() {
        AttemptSolvedEvent attemptDTO = new AttemptSolvedEvent(5L, "Mohamed", 1L, 11, 20, false);
        List<ScoreCard> scoreCards = List.of();

        Optional<BadgeType> badge = firstChallengeBadgeProcessor.getBadgeOrNull(attemptDTO, 0, scoreCards);
        
        then(badge.isPresent()).isEqualTo(false);
    }


    @Test
    void testGetBadgeOrNullWithNotFirstScore() {
        AttemptSolvedEvent attemptDTO = new AttemptSolvedEvent(10L, "Mohamed", 1L, 11, 20, true);
        List<ScoreCard> scoreCards = List.of(
            new ScoreCard(1L, 3L),
            new ScoreCard(1L, 2L),            
            new ScoreCard(1L, 1L)         
        );

        Optional<BadgeType> badge = firstChallengeBadgeProcessor.getBadgeOrNull(attemptDTO, 30, scoreCards);
        
        then(badge.isPresent()).isEqualTo(false);
    }
    

    @Test
    void testGetBadgeType() {
        then(firstChallengeBadgeProcessor.getBadgeType()).isEqualTo(BadgeType.FIRST_CHALLENGE);
    }
}

package com.example.gemification.game.repository;

import static org.assertj.core.api.BDDAssertions.then;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.gemification.game.LeaderBoard;
import com.example.gemification.game.ScoreCard;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class ScoreCardRepositoryTest {
    @Autowired
    ScoreCardRepository scoreCardRepository;

    List<ScoreCard> scoreCards;

    @BeforeEach
    void setUp() {
        scoreCards = List.of(
                new ScoreCard(10L, 1L),
                new ScoreCard(10L, 2L),
                new ScoreCard(10L, 3L),
                new ScoreCard(10L, 5L),
                new ScoreCard(5L, 7L)

        );
        scoreCardRepository.saveAll(scoreCards);
    }

    @AfterEach
    void clear() {
        scoreCardRepository.deleteAll();
    }

    @Test
    void testGetTotalScore() {
        then(scoreCardRepository.getTotalScore(10L)).isEqualTo(40);
    }

    @Test
    void testGetTotalScoreWithNo() {
        scoreCardRepository.deleteAll();
        then(scoreCardRepository.getTotalScore(10L)).isEqualTo(0);
    }

    @Test
    void testGetTopScoredForTop4() {
        List<ScoreCard> additional = List.of(
                new ScoreCard(1L, 8L),
                new ScoreCard(1L, 9L),
                new ScoreCard(2L, 10L),
                new ScoreCard(3L, 11L),
                new ScoreCard(5L, 12L),
                new ScoreCard(5L, 13L), 
                new ScoreCard(2L, 14L),                
                new ScoreCard(7L, 15L),
                new ScoreCard(8L, 16L),
                new ScoreCard(9L, 17L),
                new ScoreCard(10L, 18L),                
                new ScoreCard(5L, 19L),
                new ScoreCard(1L, 17L)
        ); // 10: 5, 5: 4, 1: 3, 2: 2, 7: 1, 8: 1, 9: 1
        scoreCardRepository.saveAll(additional);
        then(scoreCardRepository.getTopScored(4)).isEqualTo(List.of(10L, 5L, 1L, 2L));
    }

    @Test
    void testGetLeaderBoard() {
        List<ScoreCard> additional = List.of(
                new ScoreCard(1L, 8L),
                new ScoreCard(1L, 9L),
                new ScoreCard(2L, 10L),
                new ScoreCard(3L, 11L),
                new ScoreCard(5L, 12L),
                new ScoreCard(5L, 13L), 
                new ScoreCard(2L, 14L),                
                new ScoreCard(7L, 15L),
                new ScoreCard(8L, 16L),
                new ScoreCard(9L, 17L),
                new ScoreCard(10L, 18L),                
                new ScoreCard(5L, 19L),
                new ScoreCard(1L, 17L)
        ); // 10: 5, 5: 4, 1: 3, 2: 2, 7: 1, 8: 1, 9: 1
        scoreCardRepository.saveAll(additional);

        List<LeaderBoard> expectedResult = List.of(
            new LeaderBoard(10L, 50l),            
            new LeaderBoard(5L, 40l),
            new LeaderBoard(1L, 30l),
            new LeaderBoard(2L, 20l)
        );
        List<LeaderBoard> result = scoreCardRepository.getLeadingBoard(4);
        then(result.size()).isEqualTo(expectedResult.size());

        for (int i = 0, len = expectedResult.size() ; i < len ; i++) {
            then(result.get(i)).isEqualTo(expectedResult.get(i));
        }
    }

}

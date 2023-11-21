package com.example.gemification.game;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScoreCard {
    public static final int DEFAULT_SCORE = 10;

    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    private Long attemptId;
    @EqualsAndHashCode.Exclude
    private long timestamp;
    private int score;

    public ScoreCard(Long userId, Long attemptId) {
        this(null, userId, attemptId, System.currentTimeMillis(), ScoreCard.DEFAULT_SCORE);
    }

    public static int getTotalScore(List<ScoreCard> scoreCards) {
        return scoreCards.stream().reduce(0, (cum, sc)-> cum+sc.getScore(), Integer::sum);
    }

}

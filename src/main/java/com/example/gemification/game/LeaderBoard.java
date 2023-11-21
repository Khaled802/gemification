package com.example.gemification.game;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.With;

@Value
@AllArgsConstructor
public class LeaderBoard {
    Long userId;
    long totalScore;

    @With
    List<String> badges;

    public LeaderBoard(final Long userId, final Long totalScore) {
        this.userId = userId;
        this.totalScore = totalScore;
        this.badges = List.of();
    }
}
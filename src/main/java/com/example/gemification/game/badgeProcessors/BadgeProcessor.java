package com.example.gemification.game.badgeProcessors;

import java.util.List;
import java.util.Optional;

import com.example.gemification.game.ScoreCard;
import com.example.gemification.game.DTO.AttemptSolvedEvent;
import com.example.gemification.game.enums.BadgeType;

public interface BadgeProcessor {
    Optional<BadgeType> getBadgeOrNull(AttemptSolvedEvent attemptDTO, int totalScore, List<ScoreCard> scoreCards);
    BadgeType getBadgeType();
}

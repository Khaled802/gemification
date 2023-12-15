package com.example.gemification.game.badgeProcessors;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.gemification.game.ScoreCard;
import com.example.gemification.game.DTO.AttemptSolvedEvent;
import com.example.gemification.game.enums.BadgeType;

@Component
public class FirstChallengeBadgeProcessor implements BadgeProcessor {

    @Override
    public Optional<BadgeType> getBadgeOrNull(AttemptSolvedEvent attemptDTO, int totalScore, List<ScoreCard> scoreCards) {
        return scoreCards.size() == 1 ? Optional.of(getBadgeType()) : Optional.empty();
    }

    @Override
    public BadgeType getBadgeType() {
        return BadgeType.FIRST_CHALLENGE;
    }

}

package com.example.gemification.game.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gemification.game.BadgeCard;
import com.example.gemification.game.ScoreCard;
import com.example.gemification.game.DTO.AttemptSolvedEvent;
import com.example.gemification.game.DTO.GameResultDTO;
import com.example.gemification.game.badgeProcessors.BadgeProcessor;
import com.example.gemification.game.enums.BadgeType;
import com.example.gemification.game.repository.BadgeCardRepository;
import com.example.gemification.game.repository.ScoreCardRepository;

@Service
public class GameServiceImp implements GameService {
    @Autowired
    ScoreCardRepository scoreCardRepository;

    @Autowired
    BadgeCardRepository badgeCardRepository;

    @Autowired
    List<BadgeProcessor> badgeProcessors;


    @Override
    public GameResultDTO addNewAttempt(AttemptSolvedEvent attemptDTO) {
        if (!attemptDTO.isCorrect()) {
            return new GameResultDTO(0, List.of());
        }

        ScoreCard newScoreCard = createScoreCard(attemptDTO);
        List<BadgeCard> newBadgeCards = creaBadgeCards(attemptDTO);
        
        return new GameResultDTO(newScoreCard.getScore(), newBadgeCards);
    }

    ScoreCard createScoreCard(AttemptSolvedEvent attemptDTO) {
        ScoreCard scoreCard = new ScoreCard(attemptDTO.getUserId(), attemptDTO.getId());
        return scoreCardRepository.save(scoreCard);
    }

    List<BadgeCard> creaBadgeCards(AttemptSolvedEvent attemptDTO) {
        Long userId = attemptDTO.getUserId();
        List<ScoreCard> scoreCards = scoreCardRepository.findByUserId(userId);
        List<BadgeCard> badgeCards = badgeCardRepository.findByUserId(userId);
        int totalScore = scoreCardRepository.getTotalScore(userId);

        List<BadgeType> newBadgeTypes = badgeProcessors.stream()
                .map((badgeProcessor) -> badgeProcessor.getBadgeOrNull(attemptDTO, totalScore, scoreCards))
                .filter((optionalBadge) -> optionalBadge.isPresent())
                .map((optionalBadge) -> optionalBadge.get())
                .filter((badgeType) -> !BadgeCard.isListHasType(badgeCards, badgeType))
                .toList();

                
            List<BadgeCard> newBadgeCards = newBadgeTypes.stream()
                .map((badgeType) -> new BadgeCard(userId, badgeType)).toList();
        return badgeCardRepository.saveAll(newBadgeCards);
    }

}

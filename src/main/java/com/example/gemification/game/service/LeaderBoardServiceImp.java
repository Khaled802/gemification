package com.example.gemification.game.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gemification.game.LeaderBoard;
import com.example.gemification.game.repository.BadgeCardRepository;
import com.example.gemification.game.repository.ScoreCardRepository;

@Service
public class LeaderBoardServiceImp implements LeaderBoardService {
    @Autowired
    ScoreCardRepository scoreCardRepository;

    @Autowired
    BadgeCardRepository badgeCardRepository;

    @Override
    public List<LeaderBoard> getLeaderBoard() {
        List<LeaderBoard> leaderBoardResult = scoreCardRepository.getLeadingBoard(4);

        return leaderBoardResult.stream().map((ele) -> {
            List<String> badges = badgeCardRepository.findByUserId(ele.getUserId()).stream()
                    .map((badge) -> badge.getBadgeType().getDescription()).toList();
            return ele.withBadges(badges);
        }).toList();
    }

}

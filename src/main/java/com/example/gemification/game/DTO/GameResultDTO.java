package com.example.gemification.game.DTO;

import java.util.List;

import com.example.gemification.game.BadgeCard;

import lombok.Value;

@Value
public class GameResultDTO {
    int totalScore;
    List<BadgeCard> badges;
}

package com.example.gemification.game;

import java.util.List;

import com.example.gemification.game.enums.BadgeType;

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
public class BadgeCard {
    @Id
    @GeneratedValue
    private Long id;
    @EqualsAndHashCode.Exclude
    private long timestamp;
    private BadgeType badgeType;
    private Long userId;

    public BadgeCard(Long userId, BadgeType badgeType) {
        this(null, System.currentTimeMillis(), badgeType, userId);
    }
    
    public boolean isThisType(BadgeType badgeType) {
        return this.badgeType.equals(badgeType);
    }

    public static boolean isListHasType(List<BadgeCard> badgeCards, BadgeType badgeType) {
        return badgeCards.stream().filter((badgeCard)-> badgeCard.isThisType(badgeType)).count() != 0;
    }
}

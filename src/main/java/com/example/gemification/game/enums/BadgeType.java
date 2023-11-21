package com.example.gemification.game.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Getter
public enum BadgeType {
    BRONZE("Bronze"),
    SILVER("Silver"),
    GOLD("Gold"),
    FIRST_CHALLENGE("First Challenge");

    private final String description;
}

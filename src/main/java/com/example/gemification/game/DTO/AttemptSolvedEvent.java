package com.example.gemification.game.DTO;

import lombok.Value;

@Value
public class AttemptSolvedEvent {
    Long id;
    String userAlies;
    Long userId;
    int factor1;
    int factor2;
    boolean correct;
}

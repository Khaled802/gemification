package com.example.gemification.game.DTO;

import lombok.Value;

@Value
public class AttemptDTO {
    Long id;
    String userAlies;
    Long userId;
    int factor1;
    int factor2;
    boolean correct;
}

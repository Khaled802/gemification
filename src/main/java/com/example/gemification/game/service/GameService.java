package com.example.gemification.game.service;

import com.example.gemification.game.DTO.AttemptDTO;
import com.example.gemification.game.DTO.GameResultDTO;

public interface GameService {
    GameResultDTO addNewAttempt(AttemptDTO attemptDTO);
}

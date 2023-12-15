package com.example.gemification.game.service;

import com.example.gemification.game.DTO.AttemptSolvedEvent;
import com.example.gemification.game.DTO.GameResultDTO;

public interface GameService {
    GameResultDTO addNewAttempt(AttemptSolvedEvent attemptDTO);
}

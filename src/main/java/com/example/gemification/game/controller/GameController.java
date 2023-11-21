package com.example.gemification.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gemification.game.DTO.AttemptDTO;
import com.example.gemification.game.DTO.GameResultDTO;
import com.example.gemification.game.service.GameService;

@RestController
@RequestMapping("/attempts")
public class GameController {

    @Autowired
    GameService gameService;
    
    @PostMapping
    public GameResultDTO newAttempt(@RequestBody AttemptDTO body) {
        return gameService.addNewAttempt(body);
    }
}

package com.example.gemification.game.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gemification.game.DTO.AttemptSolvedEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GameEventHandler {
    @Autowired
    GameService gameService;

    @RabbitListener(queues = "${amqp.queue.gamification}")
    public void handleAttemptSolvedEvent(final AttemptSolvedEvent attemptSolvedEvent) {
        log.info("new solved attempt {}}", attemptSolvedEvent);
        gameService.addNewAttempt(attemptSolvedEvent);
    }
    
}

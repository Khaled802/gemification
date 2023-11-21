package com.example.gemification.game.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gemification.game.LeaderBoard;
import com.example.gemification.game.service.LeaderBoardService;

@RestController
@RequestMapping("/leaderboard")
public class LeaderBoardController {
    @Autowired
    LeaderBoardService leaderBoardService;

    @GetMapping
    public List<LeaderBoard> getLeaderBoard() {
        return leaderBoardService.getLeaderBoard();
    }
}

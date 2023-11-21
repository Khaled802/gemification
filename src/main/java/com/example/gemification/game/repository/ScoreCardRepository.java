package com.example.gemification.game.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.gemification.game.LeaderBoard;
import com.example.gemification.game.ScoreCard;

@Repository
public interface ScoreCardRepository extends JpaRepository<ScoreCard, Long> {
    public List<ScoreCard> findByUserId(Long userId);

    @Query("SELECT COALESCE(SUM(s.score), 0) FROM ScoreCard s WHERE s.userId=:userId")
    public Integer getTotalScore(Long userId);

    @Query("SELECT userId FROM ScoreCard s GROUP BY s.userId ORDER BY SUM(s.score) DESC LIMIT :topNum")
    public List<Long> getTopScored(int topNum);

    @Query("SELECT new com.example.gemification.game.LeaderBoard(s.userId, SUM(s.score)) FROM ScoreCard s GROUP BY s.userId ORDER BY SUM(s.score) DESC LIMIT :topNum")
    public List<LeaderBoard> getLeadingBoard(int topNum);
}

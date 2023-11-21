package com.example.gemification.game.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gemification.game.BadgeCard;

public interface BadgeCardRepository extends JpaRepository<BadgeCard, Long> {
    public List<BadgeCard> findByUserId(Long userId);
}

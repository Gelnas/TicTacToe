package com.example.TicTacToe.repository;

import com.example.TicTacToe.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}

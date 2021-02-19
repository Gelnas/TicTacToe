package com.example.TicTacToe.repository;

import com.example.TicTacToe.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}

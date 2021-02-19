package com.example.TicTacToe.repository;

import com.example.TicTacToe.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findByEmail(String email);
}

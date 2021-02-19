package com.example.TicTacToe.mapper.dto;

import com.example.TicTacToe.dto.request.PlayerRequest;
import com.example.TicTacToe.model.Player;
import org.springframework.core.convert.converter.Converter;

public class PlayerRequestToPlayerConverter implements Converter<PlayerRequest, Player> {

    @Override
    public Player convert(PlayerRequest playerRequest) {
        return Player.builder()
                .username(playerRequest.getName())
                .email(playerRequest.getEmail())
                .password(playerRequest.getPassword())
                .countDefeat(0)
                .countWins(0)
                .build();
    }
}

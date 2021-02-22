package com.example.TicTacToe.mapper.model;

import com.example.TicTacToe.dto.response.PlayerStatisticsResponse;
import com.example.TicTacToe.model.Player;
import org.springframework.core.convert.converter.Converter;

public class PlayerToPlayerStatisticsResponseConverter implements Converter<Player, PlayerStatisticsResponse> {

    @Override
    public PlayerStatisticsResponse convert(Player player) {
        return PlayerStatisticsResponse.builder()
                .username(player.getUsername())
                .countWins(player.getCountWins())
                .countDefeat(player.getCountDefeat())
                .countDraw(player.getCountDraw())
                .build();
    }
}

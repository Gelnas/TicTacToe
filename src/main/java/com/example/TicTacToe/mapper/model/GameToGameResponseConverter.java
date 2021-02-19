package com.example.TicTacToe.mapper.model;

import org.springframework.core.convert.converter.Converter;
import com.example.TicTacToe.dto.response.GamesResponse;
import com.example.TicTacToe.model.Game;

public class GameToGameResponseConverter  implements Converter<Game, GamesResponse> {

    @Override
    public GamesResponse convert(Game game) {
        return GamesResponse.builder()
                .id(game.getId())
                .build();
    }
}

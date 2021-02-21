package com.example.TicTacToe.mapper.model;

import org.springframework.core.convert.converter.Converter;
import com.example.TicTacToe.dto.response.GameResponse;
import com.example.TicTacToe.model.Game;

public class GameToGameResponseConverter  implements Converter<Game, GameResponse> {

    @Override
    public GameResponse convert(Game game) {
        return GameResponse.builder()
                .id(game.getId())
                .status(game.getStatus())
                .field(game.getField())
                .build();
    }
}

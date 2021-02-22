package com.example.TicTacToe.mapper.model;

import com.example.TicTacToe.dto.response.GameCourseResponse;
import com.example.TicTacToe.model.Game;
import org.springframework.core.convert.converter.Converter;

public class GameToGameCourseResponseConvert implements Converter<Game, GameCourseResponse> {


    @Override
    public GameCourseResponse convert(Game game) {
        return GameCourseResponse.builder()
                .id(game.getId())
                .status(game.getStatus())
                .field(game.getField())
                .updated(game.getUpdated())
                .build();
    }
}

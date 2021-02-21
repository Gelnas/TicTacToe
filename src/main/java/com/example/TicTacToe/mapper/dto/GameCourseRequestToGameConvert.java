package com.example.TicTacToe.mapper.dto;

import com.example.TicTacToe.dto.request.GameCourseRequest;
import com.example.TicTacToe.model.Game;
import com.example.TicTacToe.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;

@RequiredArgsConstructor
public class GameCourseRequestToGameConvert implements Converter<GameCourseRequest, Game> {
    public final GameService gameService;

    @Override
    public Game convert(GameCourseRequest gameCourseRequest) {

        return Game.builder()
                .field(updateField(gameCourseRequest.getId(),
                        gameCourseRequest.getSymbol(), gameCourseRequest.getCoordinates()))
                .build();
    }

    private String updateField(Long id, String symbol, String coordinates){
        String field = gameService.getById(id).getField();
        String[][] result = gameService.toStringArray(field);
        int x = coordinates.charAt(0) == '0' ? 0 : coordinates.charAt(0) == '1' ? 1 : 2;
        int y = coordinates.charAt(1) == '0' ? 0 : coordinates.charAt(1) == '1' ? 1 : 2;

        if ( x >= 0 && x <= 2 && y >= 0 && y <= 2 && result[x][y].equals("?")){
            result[x][y] = symbol;
        }
        return gameService.toString(result);
    }
}

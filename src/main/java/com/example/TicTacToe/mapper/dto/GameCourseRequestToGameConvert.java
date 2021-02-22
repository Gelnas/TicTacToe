package com.example.TicTacToe.mapper.dto;

import com.example.TicTacToe.dto.request.GameCourseRequest;
import com.example.TicTacToe.exception.WrongMoveException;
import com.example.TicTacToe.exception.WrongSymbolException;
import com.example.TicTacToe.model.Game;
import com.example.TicTacToe.model.HistoryMoves;
import com.example.TicTacToe.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

@RequiredArgsConstructor
public class GameCourseRequestToGameConvert implements Converter<GameCourseRequest, Game> {
    public final GameService gameService;

    @Override
    public Game convert(GameCourseRequest gameCourseRequest) {
        String field = updateField(gameCourseRequest.getId(),
                gameCourseRequest.getSymbol(), gameCourseRequest.getCoordinates());
        List<HistoryMoves> historyMoves = getHistoryMoves(gameCourseRequest.getId(),
                gameCourseRequest.getSymbol(), gameCourseRequest.getCoordinates());

        return Game.builder()
                .field(field)
                .historyMoves(historyMoves)
                .build();
    }

    private String updateField(Long id, String symbol, String coordinates){
        if(!symbol.equals("x") && !symbol.equals("0")){
            throw new WrongSymbolException("You are using the wrong symbol. Use x or 0");
        }
        String field = gameService.getById(id).getField();
        String[][] result = gameService.toStringArray(field);
        int x = coordinates.charAt(0) == '0' ? 0 : coordinates.charAt(0) == '1' ? 1 : 2;
        int y = coordinates.charAt(1) == '0' ? 0 : coordinates.charAt(1) == '1' ? 1 : 2;

        if ( x >= 0 && x <= 2 && y >= 0 && y <= 2 && result[x][y].equals("?")){
            result[x][y] = symbol;
        } else {
            throw new WrongMoveException("You're trying to go wrong.");
        }
        return gameService.toString(result);
    }

    private List<HistoryMoves> getHistoryMoves(Long id, String symbol, String coordinates){
        List<HistoryMoves> result = gameService.getById(id).getHistoryMoves();
        HistoryMoves move = HistoryMoves.builder()
                .number(result.size() + 1)
                .position(coordinates)
                .symbol(symbol)
                .game(gameService.getById(id))
                .build();
        result.add(move);
        return result;
    }
}

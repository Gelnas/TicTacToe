package com.example.TicTacToe.mapper.model;

import com.example.TicTacToe.dto.response.HistoryGameMovesResponse;
import com.example.TicTacToe.dto.response.HistoryMovesResponse;
import com.example.TicTacToe.model.Game;
import com.example.TicTacToe.model.HistoryMoves;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public class GameToHistoryGameMovesResponseConvert implements Converter<Game, HistoryGameMovesResponse> {

    @Override
    public HistoryGameMovesResponse convert(Game game) {
        List<HistoryMovesResponse> historyMovesResponses = toHistoryGameMovesResponseList(game.getHistoryMoves());
        return HistoryGameMovesResponse.builder()
                .id(game.getId())
                .status(game.getStatus())
                .field(game.getField())
                .historyMovesResponse(historyMovesResponses)
                .build();
    }

    private  List<HistoryMovesResponse> toHistoryGameMovesResponseList(List<HistoryMoves> list){
        if (list == null){
            return null;
        }

        List<HistoryMovesResponse> result = new ArrayList<>();
        for(HistoryMoves move : list){
            result.add(toHistoryMovesResponse(move));
        }
        return result;
    }

    private HistoryMovesResponse toHistoryMovesResponse(HistoryMoves move) {
        if(move == null){
            return null;
        }

        return HistoryMovesResponse.builder()
                .number(move.getNumber())
                .position(move.getPosition())
                .symbol(move.getSymbol())
                .build();
    }
}

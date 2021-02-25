package com.example.TicTacToe.mapper.dto;

import com.example.TicTacToe.dto.request.CreateGameRequest;
import com.example.TicTacToe.exception.WrongPlayerException;
import com.example.TicTacToe.model.Game;
import com.example.TicTacToe.model.GameStatus;
import com.example.TicTacToe.model.Player;
import com.example.TicTacToe.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CreateGameRequestToGameConverter implements Converter<CreateGameRequest, Game> {
    private final PlayerService playerService;
    String EMPTY_FIELD = "?~?~? ?~?~? ?~?~?";

    @Override
    public Game convert(CreateGameRequest gameRequest) {
        List<Player> list = toPlayerList(gameRequest);

        return Game.builder()
                .status(GameStatus.IN_PROGRESS)
                .field(EMPTY_FIELD)
                .players(list)
                .build();
    }

    private List<Player> toPlayerList(CreateGameRequest gameRequest) {
        List<Player> list = new ArrayList<>();
        Long playersId = gameRequest.getPlayer_id();
        Long opponentId = gameRequest.getOpponent_id();
        if (playersId == opponentId){
            throw new WrongPlayerException("you chose yourself as your opponent");
        }
        list.add(playerService.getById(playersId));
        list.add(playerService.getById(opponentId));
        return list;
    }
}

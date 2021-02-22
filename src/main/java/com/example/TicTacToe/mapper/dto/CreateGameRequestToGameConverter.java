package com.example.TicTacToe.mapper.dto;

import com.example.TicTacToe.dto.request.CreateGameRequest;
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

        list.add(playerService.getById(gameRequest.getPlayer_id()));
        list.add(playerService.getById(gameRequest.getOpponent_id()));
        return list;
    }

}

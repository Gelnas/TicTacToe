package com.example.TicTacToe.mapper.model;

import com.example.TicTacToe.dto.response.GameResponse;
import com.example.TicTacToe.dto.response.PlayerResponse;
import com.example.TicTacToe.model.Game;
import com.example.TicTacToe.model.Player;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public class PlayerToPlayerResponseConverter implements Converter<Player, PlayerResponse> {

    @Override
    public PlayerResponse convert(Player player) {
       List<GameResponse> list = toGameResponseList(player.getGames());

        return PlayerResponse.builder()
                .id(player.getId())
                .username(player.getUsername())
                .email(player.getEmail())
                .games(list)
                .countWins(player.getCountWins())
                .countDefeat(player.getCountDefeat())
                .countDraw(player.getCountDraw())
                .build();
    }

    private List<GameResponse> toGameResponseList(List<Game> list) {
        if (list == null) {
            return null;
        }

        List<GameResponse> restList = new ArrayList<>();
        for(Game game : list){
            restList.add(toGameResponse(game));
        }
        return restList;
    }

    private GameResponse toGameResponse(Game game){
        if(game == null){
            return null;
        }
        return GameResponse.builder()
                .id(game.getId())
                .status(game.getStatus())
                .field(game.getField())
                .build();
    }
}

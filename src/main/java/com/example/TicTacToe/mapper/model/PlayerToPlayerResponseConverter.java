package com.example.TicTacToe.mapper.model;

import com.example.TicTacToe.dto.response.PlayerResponse;
import com.example.TicTacToe.model.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;

@RequiredArgsConstructor
public class PlayerToPlayerResponseConverter implements Converter<Player, PlayerResponse> {
    private final GameToGameResponseConverter toGameResponseConverter;

    @Override
    public PlayerResponse convert(Player player) {
      // List<GamesResponse> list = toGameResponseList(player.getGames());

        return PlayerResponse.builder()
                .id(player.getId())
                .username(player.getUsername())
                .email(player.getEmail())
               // .games(list)
                .countWins(player.getCountWins())
                .countDefeat(player.getCountDefeat())
                .build();
    }

//    private List<GamesResponse> toGameResponseList(List<Game> list) {
//        if (list == null) {
//            return null;
//        }
//
//        List<GamesResponse> restList = new ArrayList<>();
//        for(Game game : list){
//            restList.add(toGameResponse(game));
//        }
//
//
//        return restList;
//    }
//
//    private GamesResponse toGameResponse(Game game){
//        if(game == null){
//            return null;
//        }
//        return GamesResponse.builder()
//                .id(game.getId())
//                .status(game.getStatus())
//                .field(game.getField())
//                .build();
//    }
}

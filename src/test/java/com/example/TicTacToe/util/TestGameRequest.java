package com.example.TicTacToe.util;

import com.example.TicTacToe.dto.request.CreateGameRequest;
import com.example.TicTacToe.dto.request.GameCourseRequest;

public class TestGameRequest {

    public static CreateGameRequest getGameRequest(){
        return CreateGameRequest.builder()
                .player_id(1L)
                .opponent_id(2L)
                .build();
    }

    public static GameCourseRequest getGameRequestForUpdate(){
        return GameCourseRequest.builder()
                .id(2L)
                .coordinates("00")
                .symbol("x")
                .build();
    }
}

package com.example.TicTacToe.util;

import com.example.TicTacToe.dto.request.CreatePlayerRequest;
import com.example.TicTacToe.dto.request.PlayerRequest;

public class TestPlayerRequest {

    public static CreatePlayerRequest getPlayerRequest(){
        return CreatePlayerRequest.builder()
                .name("Rick")
                .email("Rick@gmail.com")
                .password("12345")
                .role("ADMIN")
                .build();
    }

    public static PlayerRequest getPlayerRequestForUpdate(){
        return PlayerRequest.builder()
                .name("Morty")
                .email("")
                .password("")
                .build();
    }
}

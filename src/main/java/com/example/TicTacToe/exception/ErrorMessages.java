package com.example.TicTacToe.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorMessages {

    NULL_ID("Id can not be null"),
    NULL_PLAYER_OBJECT("Player can not be null"),
    NULL_GAME_OBJECT("Game can not be null");

    private final String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

}

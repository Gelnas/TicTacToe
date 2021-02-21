package com.example.TicTacToe.exception;

public class WrongMoveException extends RuntimeException{

    public WrongMoveException(String message) {
        super(message);
    }
}

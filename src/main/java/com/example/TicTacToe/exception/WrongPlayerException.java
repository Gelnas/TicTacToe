package com.example.TicTacToe.exception;

public class WrongPlayerException extends RuntimeException{

    public WrongPlayerException(String message) {
        super(message);
    }
}

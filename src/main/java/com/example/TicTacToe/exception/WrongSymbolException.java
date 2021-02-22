package com.example.TicTacToe.exception;

public class WrongSymbolException extends RuntimeException{

    public WrongSymbolException(String message){
        super(message);
    }
}

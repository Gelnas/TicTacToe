package com.example.TicTacToe;

public interface Urls {
    String ROOT = "/";

    public interface Player {
        String PART = "player";
        String FULL = ROOT + PART;
    }

    public interface Game {
        String PART = "game";
        String FULL = ROOT + PART;
    }
}

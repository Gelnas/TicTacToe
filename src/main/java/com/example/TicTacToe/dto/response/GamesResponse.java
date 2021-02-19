package com.example.TicTacToe.dto.response;

import com.example.TicTacToe.model.GameStatus;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Object for transferring data from a controller to a response about list of player games.
 */
@Data
@Builder
public class GamesResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Unique identifier of the user
     */
    private Long id;

    /**
     * Information about game status
     */
    private GameStatus status;

    /**
     * Playing field
     */
    private Character[][] field;
}

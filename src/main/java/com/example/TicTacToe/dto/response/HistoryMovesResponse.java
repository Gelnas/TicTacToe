package com.example.TicTacToe.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * Object for transmitting data from the controller to the response about the history of moves in the game.
 */
@Data
@Builder
public class HistoryMovesResponse {

    /**
     * Number of move
     */
    @Schema(description = "Number of move",
            example = "1")
    private int number;

    /**
     * Move position
     */
    @Schema(description = "Move position",
            example = "11")
    private String position;

    /**
     * The symbol used during the move
     */
    @Schema(description = "The symbol used during the move",
            example = "x")
    private String symbol;
}

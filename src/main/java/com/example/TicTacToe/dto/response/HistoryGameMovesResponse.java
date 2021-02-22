package com.example.TicTacToe.dto.response;

import com.example.TicTacToe.model.GameStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Object for transmitting data from the controller to the response about the history of moves in the game.
 */
@Data
@Builder
public class HistoryGameMovesResponse {

    /**
     * Unique identifier of the user
     */
    @Schema(description = "an unique identifier",
            example = "1")
    private final Long id;

    /**
     * Information about game status
     */
    @Schema(description = "game status",
            example = "IN_PROGRESS")
    private final GameStatus status;

    /**
     * Playing field
     */
    @Schema(description = "Playing field")
    private final String field;

    /**
     * History of moves
     */
    private final List<HistoryMovesResponse> historyMovesResponse;

}

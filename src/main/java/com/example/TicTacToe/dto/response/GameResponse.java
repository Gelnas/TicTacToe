package com.example.TicTacToe.dto.response;

import com.example.TicTacToe.model.GameStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Object for transferring data from a controller to a response about list of player games.
 */
@Data
@Builder
public class GameResponse implements Serializable {
    private static final long serialVersionUID = 1L;

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
}

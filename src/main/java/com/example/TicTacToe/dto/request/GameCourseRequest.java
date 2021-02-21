package com.example.TicTacToe.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Object for transmitting data from the request to the controller to update the game.
 */
@Data
@Builder
public class GameCourseRequest {

    /**
     * Unique identifier of the user
     */
    @Schema(description = "an unique identifier",
            example = "1")
    private Long id;

    /**
     * The symbol of the player
     */
    @Schema(description = "The symbol of the player. x or 0", example = "x", required = true)
    @NotNull(message = "The field is required")
    private final String symbol;

    /**
     * Coordinates of the player's turn
     */
    @Schema(description = "coordinates of the player's turn", example = "22", required = true)
    @NotNull(message = "The field is required")
    private final String coordinates;

}

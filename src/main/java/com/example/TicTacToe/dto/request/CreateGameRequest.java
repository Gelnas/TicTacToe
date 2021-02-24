package com.example.TicTacToe.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Object for transmitting data from the request to the controller to create a new game.
 */
@Data
@Builder
public class CreateGameRequest {

    /**
     * The player's ID
     */
    @Schema(description = "The player's ID.", example = "1", required = true)
    @NotNull(message = "The field is required")
    private final Long player_id;

    /**
     * The opponent's ID
     */
    @Schema(description = "The opponent's ID. If the ID is zero, " +
            "then the player wants to play with the computer", example = "1", required = true)
    @NotNull(message = "The field is required")
    private final Long opponent_id;
}

package com.example.TicTacToe.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Object for transmitting data from the request to the controller to create a new player.
 */
@Data
@Builder
public class CreatePlayerRequest {

    /**
     * Name of the new player
     */
    @Schema(description = "Name of the new player", required = true)
    @NotNull(message = "The field is required")
    private final String name;

    /**
     * New player's password
     */
    @Schema(description = "New player's password", required = true)
    @NotNull(message = "The field is required")
    private final String password;

    /**
     * New player's email
     */
    @Schema(description = "New player's email", required = true)
    @NotNull(message = "The field is required")
    private final String email;

    /**
     * New player's role
     */
    @Schema(description = "New player's role. USER or ADMIN", required = true)
    @NotNull(message = "The field is required")
    private final String role;
}

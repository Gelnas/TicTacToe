package com.example.TicTacToe.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Object for transferring data from a controller to a response about a player.
 */
@Data
@Builder
public class PlayerResponse {

    /**
     * Unique identifier of the delivery
     */
    @Schema(description = "an unique identifier",
            example = "1")
    private final Long id;

    /**
     * Name of the new player
     */
    @Schema(description = "user name",
            example = "Alex")
    private final String username;

    /**
     * Player's email
     */
    @Schema(description = "User's email",
            example = "Alex@gmail.com")
    private final String email;

    /**
     * List of player games
     */
    @Schema(description = "List of player games")
    private final List<GamesResponse> games;

    /**
     * Count of player wins
     */
    @Schema(description = "Count of user wins",
            example = "1")
    private Integer countWins;

    /**
     * Count of player defeat
     */
    @Schema(description = "Count of user defeat",
            example = "1")
    private Integer countDefeat;
}

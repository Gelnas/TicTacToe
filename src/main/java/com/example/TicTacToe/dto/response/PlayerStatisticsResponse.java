package com.example.TicTacToe.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * Object for transferring data from a controller to a response about a player.
 */
@Data
@Builder
public class PlayerStatisticsResponse {

    /**
     * Name of the new player
     */
    @Schema(description = "user name",
            example = "Alex")
    private final String username;

    /**
     * Count of player wins
     */
    @Schema(description = "Count of user wins",
            example = "1")
    private final Integer countWins;

    /**
     * Count of player defeat
     */
    @Schema(description = "Count of user defeat",
            example = "1")
    private final Integer countDefeat;


    /**
     * Count of games in a draw
     */
    @Schema(description = "Count of games in a draw",
            example = "0")
    private Integer countDraw;
}

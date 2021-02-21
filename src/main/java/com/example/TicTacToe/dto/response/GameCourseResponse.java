package com.example.TicTacToe.dto.response;

import com.example.TicTacToe.model.GameStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Object for transferring data from a controller to a response about player's progress
 */
@Data
@Builder
public class GameCourseResponse {

    /**
     * Unique identifier of the user
     */
    @Schema(description = "an unique identifier",
            example = "1")
    private Long id;

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
     * Update time
     */
    @Schema(description = "Update time")
    private final LocalDateTime updated;
}

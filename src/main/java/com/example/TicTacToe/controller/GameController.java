package com.example.TicTacToe.controller;

import com.example.TicTacToe.Urls;
import com.example.TicTacToe.dto.request.CreateGameRequest;
import com.example.TicTacToe.dto.request.GameCourseRequest;
import com.example.TicTacToe.dto.request.PlayerRequest;
import com.example.TicTacToe.dto.response.GameCourseResponse;
import com.example.TicTacToe.dto.response.GameResponse;
import com.example.TicTacToe.dto.response.PlayerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Tag(name = "Game")
@RequestMapping(Urls.Game.FULL)
public interface GameController {
    String ID_PATH_VARIABLE = "/{id}";

    @PreAuthorize("hasAuthority('CREATE')")
    @Operation(summary = "create a new game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "a game created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GameResponse.class))),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)
    })
    @PostMapping("/create")
    ResponseEntity<GameResponse> create(
            @Parameter(
                    description = "the game to add. Cannot be null.",
                    required = true,
                    schema = @Schema(implementation = CreateGameRequest.class))
            @Valid @RequestBody CreateGameRequest gameRequest);


    @PreAuthorize("hasAuthority('UPDATE')")
    @Operation(summary = "player's turn")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GameCourseResponse.class))),
            @ApiResponse(responseCode = "404", description = "not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)
    })
    @PutMapping(ID_PATH_VARIABLE)
    ResponseEntity<GameCourseResponse> update(
            @Parameter(
                    name = "id",
                    description = "id of the game, which the player plays. Cannot be null.",
                    required = true)
            @PathVariable Long id,
            @Parameter(
                    description = "player's turn. Cannot be null.",
                    required = true,
                    schema = @Schema(implementation = GameCourseRequest.class))
            @Valid @RequestBody GameCourseRequest gameCourseRequest);
}

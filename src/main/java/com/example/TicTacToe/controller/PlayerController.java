package com.example.TicTacToe.controller;

import com.example.TicTacToe.Urls;
import com.example.TicTacToe.dto.request.CreatePlayerRequest;
import com.example.TicTacToe.dto.request.PlayerRequest;
import com.example.TicTacToe.dto.response.PlayerPageResponse;
import com.example.TicTacToe.dto.response.PlayerResponse;
import com.example.TicTacToe.dto.response.PlayerStatisticsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Tag(name = "Player")
@RequestMapping(Urls.Player.FULL)
public interface PlayerController {
    String ID_PATH_VARIABLE = "/{id}";

    @PreAuthorize("hasAuthority('READ')")
    @Operation(summary = "get a player by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PlayerResponse.class))),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "404", description = "not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)
    })
    @GetMapping(ID_PATH_VARIABLE)
    ResponseEntity<PlayerResponse> getById(
            @Parameter(
                    name = "id",
                    description = "id  of the player to be obtained. Cannot be null",
                    required = true)
            @PathVariable Long id);

    @PreAuthorize("hasAuthority('READ')")
    @Operation(summary = "get a page of players")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PlayerPageResponse.class))),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)
    })
    @GetMapping
    ResponseEntity<PlayerPageResponse> getList(
            @Parameter(
                    name = "pageable",
                    description = "parameters of the page. Cannot be null",
                    required = true,
                    schema = @Schema(implementation = Pageable.class))
                    Pageable pageable);

    @PreAuthorize("hasAuthority('READ')")
    @Operation(summary = "get a player's statistics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PlayerStatisticsResponse.class))),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "404", description = "not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)
    })
    @GetMapping(Urls.Player.Statistics.FULL)
    ResponseEntity<PlayerStatisticsResponse> getStatistics();

    @PreAuthorize("hasAuthority('READ')")
    @Operation(summary = "get the top 10 players")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PlayerPageResponse.class))),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)
    })
    @GetMapping(Urls.Player.Sort.FULL)
    ResponseEntity<PlayerPageResponse> getSortList();

    @Operation(summary = "create a new player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "a player created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PlayerResponse.class))),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)
    })
    @PostMapping(Urls.Player.Create.FULL)
    ResponseEntity<PlayerResponse> create(
            @Parameter(
                    description = "the player to add. Cannot be null.",
                    required = true,
                    schema = @Schema(implementation = CreatePlayerRequest.class))
            @Valid @RequestBody CreatePlayerRequest createPlayerRequest);

    @PreAuthorize("hasAuthority('WRITE')")
    @Operation(summary = "update an existing player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PlayerResponse.class))),
            @ApiResponse(responseCode = "404", description = "not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)
    })
    @PutMapping(ID_PATH_VARIABLE)
    ResponseEntity<PlayerResponse> update(
            @Parameter(
                    name = "id",
                    description = "id of the player to be updated. Cannot be null.",
                    required = true)
            @PathVariable Long id,
            @Parameter(
                    description = "the player to be updated. Cannot be null.",
                    required = true,
                    schema = @Schema(implementation = PlayerRequest.class))
            @Valid @RequestBody PlayerRequest playerRequest);

    @PreAuthorize("hasAuthority('DELETE')")
    @Operation(summary = "delete a player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "404", description = "not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)
    })
    @DeleteMapping(ID_PATH_VARIABLE)
    ResponseEntity<String> delete(
            @Parameter(
                    name = "id",
                    description = "id of the player to be deleted. Cannot be null",
                    required = true)
            @PathVariable Long id);
}

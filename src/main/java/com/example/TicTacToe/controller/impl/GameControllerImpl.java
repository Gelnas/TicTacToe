package com.example.TicTacToe.controller.impl;

import com.example.TicTacToe.controller.GameController;
import com.example.TicTacToe.dto.request.CreateGameRequest;
import com.example.TicTacToe.dto.request.GameCourseRequest;
import com.example.TicTacToe.dto.response.GameCourseResponse;
import com.example.TicTacToe.dto.response.GameResponse;
import com.example.TicTacToe.dto.response.HistoryGameMovesResponse;
import com.example.TicTacToe.model.Game;
import com.example.TicTacToe.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class GameControllerImpl implements GameController {
    private final GameService gameService;
    private final ConversionService conversionService;

    @Override
    public ResponseEntity<HistoryGameMovesResponse> getById(Long id) {
        Game game = gameService.getById(id);
        return ResponseEntity.ok(
                Objects.requireNonNull(conversionService.convert(game, HistoryGameMovesResponse.class)));
    }

    @Override
    public ResponseEntity<GameResponse> create(@Valid CreateGameRequest gameRequest) {
        Game saved = gameService.save(conversionService.convert(gameRequest, Game.class));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(conversionService.convert(saved, GameResponse.class));
    }

    @Override
    public ResponseEntity<GameCourseResponse> update(Long id, @Valid GameCourseRequest gameCourseRequest) {
        Game updated = gameService.update(id, conversionService.convert(gameCourseRequest, Game.class));
        return ResponseEntity.ok(
                Objects.requireNonNull(conversionService.convert(updated, GameCourseResponse.class)));
    }
}

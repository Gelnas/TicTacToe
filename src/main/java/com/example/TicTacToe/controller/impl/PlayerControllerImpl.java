package com.example.TicTacToe.controller.impl;

import com.example.TicTacToe.controller.PlayerController;
import com.example.TicTacToe.dto.request.CreatePlayerRequest;
import com.example.TicTacToe.dto.request.PlayerRequest;
import com.example.TicTacToe.dto.response.PlayerPageResponse;
import com.example.TicTacToe.dto.response.PlayerResponse;
import com.example.TicTacToe.dto.response.PlayerStatisticsResponse;
import com.example.TicTacToe.model.Player;
import com.example.TicTacToe.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class PlayerControllerImpl implements PlayerController {
    private final PlayerService playerService;
    private final ConversionService conversionService;

    @Override
    public ResponseEntity<PlayerResponse> getById(Long id) {
        Player player = playerService.getById(id);
        return ResponseEntity.ok(
                Objects.requireNonNull(conversionService.convert(player, PlayerResponse.class)));
    }

    @Override
    public ResponseEntity<PlayerPageResponse> getList(Pageable pageable) {
        Page<Player> page = playerService.getList(pageable);
        return ResponseEntity.ok(
                Objects.requireNonNull(conversionService.convert(page, PlayerPageResponse.class)));
    }

    @Override
    public ResponseEntity<PlayerStatisticsResponse> getStatistics() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = ((User)authentication.getPrincipal()).getUsername();
        Player saved = playerService.getByEmail(email);
        return ResponseEntity.ok(
                Objects.requireNonNull(conversionService.convert(saved, PlayerStatisticsResponse.class)));
    }

    @Override
    public ResponseEntity<PlayerResponse> create(@Valid CreatePlayerRequest createPlayerRequest) {
        Player saved = playerService.save(
                conversionService.convert(createPlayerRequest, Player.class));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(conversionService.convert(saved, PlayerResponse.class));
    }

    @Override
    public ResponseEntity<PlayerResponse> update(Long id, @Valid PlayerRequest playerRequest) {
        Player updated = playerService.update(id, conversionService.convert(playerRequest, Player.class));
        return ResponseEntity.ok(
                Objects.requireNonNull(conversionService.convert(updated, PlayerResponse.class)));
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        playerService.delete(id);
        return ResponseEntity.ok().build();
    }
}

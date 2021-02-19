package com.example.TicTacToe.mapper.model;

import com.example.TicTacToe.dto.response.PlayerPageResponse;
import com.example.TicTacToe.model.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.core.convert.converter.Converter;;import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PlayerPageToPlayerPageResponseConverter implements Converter<Page<Player>, PlayerPageResponse> {
    private final PlayerToPlayerResponseConverter toPlayerResponseConverter;
    @Override
    public PlayerPageResponse convert(Page<Player> players) {
        return PlayerPageResponse.builder()
                .content(players.getContent().stream()
                        .map(toPlayerResponseConverter::convert)
                        .collect(Collectors.toList()))
                .number(players.getNumber())
                .size(players.getSize())
                .totalElements(players.getTotalElements())
                .totalPages(players.getTotalPages())
                .build();
    }
}

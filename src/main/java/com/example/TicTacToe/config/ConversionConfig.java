package com.example.TicTacToe.config;

import com.example.TicTacToe.mapper.dto.CreateGameRequestToGameConverter;
import com.example.TicTacToe.mapper.dto.GameCourseRequestToGameConvert;
import com.example.TicTacToe.mapper.dto.PlayerRequestToPlayerConverter;
import com.example.TicTacToe.mapper.model.GameToGameCourseResponseConvert;
import com.example.TicTacToe.mapper.model.GameToGameResponseConverter;
import com.example.TicTacToe.mapper.model.PlayerPageToPlayerPageResponseConverter;
import com.example.TicTacToe.mapper.model.PlayerToPlayerResponseConverter;
import com.example.TicTacToe.service.GameService;
import com.example.TicTacToe.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class ConversionConfig implements WebMvcConfigurer {
    private final PlayerService playerService;
    private final GameService gameService;

    @Override
    public void addFormatters(FormatterRegistry registry) {

        registry.addConverter(new PlayerRequestToPlayerConverter());

        GameToGameResponseConverter toGameResponseConverter = new GameToGameResponseConverter();
        registry.addConverter(toGameResponseConverter);

        PlayerToPlayerResponseConverter toPlayerResponseConverter = new PlayerToPlayerResponseConverter(toGameResponseConverter);
        registry.addConverter(toPlayerResponseConverter);

        registry.addConverter(new PlayerPageToPlayerPageResponseConverter(toPlayerResponseConverter));

        registry.addConverter(new CreateGameRequestToGameConverter(playerService));

        registry.addConverter(new GameToGameCourseResponseConvert());

        registry.addConverter(new GameCourseRequestToGameConvert(gameService));
    }
}

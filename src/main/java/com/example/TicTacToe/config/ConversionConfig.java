package com.example.TicTacToe.config;

import com.example.TicTacToe.mapper.dto.PlayerRequestToPlayerConverter;
import com.example.TicTacToe.mapper.model.GameToGameResponseConverter;
import com.example.TicTacToe.mapper.model.PlayerPageToPlayerPageResponseConverter;
import com.example.TicTacToe.mapper.model.PlayerToPlayerResponseConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConversionConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {

        registry.addConverter(new PlayerRequestToPlayerConverter());

        GameToGameResponseConverter toGameResponseConverter = new GameToGameResponseConverter();
        registry.addConverter(toGameResponseConverter);

        PlayerToPlayerResponseConverter toPlayerResponseConverter = new PlayerToPlayerResponseConverter(toGameResponseConverter);
        registry.addConverter(toPlayerResponseConverter);

        registry.addConverter(new PlayerPageToPlayerPageResponseConverter(toPlayerResponseConverter));
    }
}

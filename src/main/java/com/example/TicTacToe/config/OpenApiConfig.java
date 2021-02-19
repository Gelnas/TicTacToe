package com.example.TicTacToe.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI coreOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                .title("Tic Tac Toe API")
                .description("This is Tic Tac Toe REST service"));
    }

}

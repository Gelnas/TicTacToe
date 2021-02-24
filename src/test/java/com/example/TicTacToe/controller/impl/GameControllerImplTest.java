package com.example.TicTacToe.controller.impl;

import com.example.TicTacToe.Urls;
import com.example.TicTacToe.dto.request.CreateGameRequest;
import com.example.TicTacToe.dto.request.GameCourseRequest;
import com.example.TicTacToe.service.impl.GameServiceImpl;
import com.example.TicTacToe.util.TestGameRequest;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application.yml")
@Sql(value = {"/controller_add_data_before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class GameControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GameControllerImpl gameController;

    @Autowired
    private GameServiceImpl gameService;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Test
    @Order(1)
    @WithMockUser(username = "Tina@gmail.com", authorities = {"READ"})
    void givenNotNullId_getById_ResponseStatusOk() throws Exception {
        Long id = 1L;

        mockMvc.perform(get(Urls.Game.FULL + "/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.status").value("WON_PLAYER1"))
                .andExpect(jsonPath("$.field").value("x~x~x .~0~. 0~.~."));
    }

    @Test
    @Order(2)
    @WithMockUser(username = "Alex@gmail.com", authorities = {"CREATE"})
    void givenNotNullRequest_whenCreate_thenResponseStatusCreated() throws Exception{
        CreateGameRequest request = TestGameRequest.getGameRequest();

        mockMvc.perform(post(Urls.Game.FULL + Urls.Game.Create.FULL)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"))
                .andExpect(jsonPath("$.field").value("?~?~? ?~?~? ?~?~?"));
    }

    @Test
    @Order(3)
    @WithMockUser(username = "Tina@gmail.com", authorities = {"UPDATE"})
    void givenNotNullIdAndRequest_whenUpdate_thenResponseStatusOk() throws Exception{
        Long id = 2L;

        GameCourseRequest updateRequest = TestGameRequest.getGameRequestForUpdate();

        mockMvc.perform(put(Urls.Game.FULL + "/" + id)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(updateRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.field").value("x~?~? ?~?~? ?~?~?"));
    }
}

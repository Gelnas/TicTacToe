package com.example.TicTacToe.controller.impl;

import com.example.TicTacToe.Urls;
import com.example.TicTacToe.dto.request.CreatePlayerRequest;
import com.example.TicTacToe.dto.request.PlayerRequest;
import com.example.TicTacToe.exception.NotFoundException;
import com.example.TicTacToe.service.impl.PlayerServiceImpl;
import com.example.TicTacToe.util.TestPlayerRequest;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
public class PlayerControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PlayerControllerImpl playerController;

    @Autowired
    private PlayerServiceImpl playerService;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Test
    @Order(1)
    void givenNotNullRequest_whenCreate_thenResponseStatusCreated() throws Exception{
        CreatePlayerRequest request = TestPlayerRequest.getPlayerRequest();

        mockMvc.perform(post(Urls.Player.FULL + Urls.Player.Create.FULL)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(4))
                .andExpect(jsonPath("$.username").value("Rick"))
                .andExpect(jsonPath("$.countWins").value(0))
                .andExpect(jsonPath("$.email").value("Rick@gmail.com"));
    }

    @Test
    @Order(2)
    @Sql(value = {"/controller_add_data_before.sql"})
    @WithMockUser(username = "Alex@gmail.com", authorities = {"READ"})
    void givenNotNullId_getById_ResponseStatusOk() throws Exception {
        Long id = 1L;

        mockMvc.perform(get(Urls.Player.FULL + "/" +id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("Tina"));
    }

    @Test
    @Order(3)
    @Sql(value = {"/controller_add_data_before.sql"})
    @WithMockUser(username = "Tina@gmail.com", authorities = {"READ"})
    void givenNotNullPageable_whenGetList_thenResponseStatusOk() throws Exception{
        mockMvc.perform(get(Urls.Player.FULL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.totalElements").value(3))
                .andExpect(jsonPath("$.size").value(20))
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[1].username").value("Alex"));
    }

    @Test
    @Order(4)
    @WithMockUser(username = "Tina@gmail.com", authorities = {"READ"})
    void givenNotNullAuthentication_whenGetStatistics_thenResponseStatusOk() throws Exception{
        mockMvc.perform(get(Urls.Player.FULL + Urls.Player.Statistics.FULL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.countWins").value(5))
                .andExpect(jsonPath("$.username").value("Tina"));
    }

    @Test
    @Order(5)
    @WithMockUser(username = "Tina@gmail.com", authorities = {"READ"})
    void whenGetSortList_thenResponseStatusOk() throws Exception{
        mockMvc.perform(get(Urls.Player.FULL + Urls.Player.Sort.FULL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.totalElements").value(2))
                .andExpect(jsonPath("$.size").value(10))
                .andExpect(jsonPath("$.content[1].id").value(1))
                .andExpect(jsonPath("$.content[0].username").value("Alex"))
                .andExpect(jsonPath("$.content[0].countWins").value(14))
                .andExpect(jsonPath("$.content[1].username").value("Tina"));
    }


    @Test
    @Order(6)
    @WithMockUser(username = "Tina@gmail.com", authorities = {"WRITE"})
    void givenNotNullIdAndRequest_whenUpdate_thenResponseStatusOk() throws Exception{
        Long id = 2L;

        PlayerRequest updateRequest = TestPlayerRequest.getPlayerRequestForUpdate();

        mockMvc.perform(put(Urls.Player.FULL + "/" + id)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(updateRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Morty"))
                .andExpect(jsonPath("$.countWins").value(14))
                .andExpect(jsonPath("$.email").value("Rick@gmail.com"));
    }

    @Test
    @Order(7)
    @WithMockUser(username = "Tina@gmail.com", authorities = {"DELETE"})
    void  givenNotNullId_whenDelete_thenResponseStatusOk() throws Exception{
        Long id = 3L;
        mockMvc.perform(delete(Urls.Player.FULL + "/" + id))
                .andDo(print())
                .andExpect(status().isOk());

        assertThrows(NotFoundException.class, () -> playerService.getById(id));
    }
}

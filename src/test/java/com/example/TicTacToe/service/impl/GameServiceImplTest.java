package com.example.TicTacToe.service.impl;

import com.example.TicTacToe.model.Game;
import com.example.TicTacToe.model.GameStatus;
import com.example.TicTacToe.model.Player;
import com.example.TicTacToe.repository.GameRepository;
import com.example.TicTacToe.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class GameServiceImplTest extends BaseTest{

    @InjectMocks
    private GameServiceImpl gameService;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private PlayerService playerService;

    private List<Game> testList;

    @BeforeEach
    void setUp(){
        prepareTestData();
    }

    private void prepareTestData() {
        testList = new ArrayList<>();
        String field1 = "?~?~? ?~?~? ?~?~?";
        String field2 = "x~x~x ?~0~? ?~0~?";
        testList.add(Game.builder()
                .id(1L)
                .status(GameStatus.IN_PROGRESS)
                .created(LocalDateTime.now())
                .field(field1)
                .build());
        testList.add(Game.builder()
                .id(2L)
                .status(GameStatus.WON_PLAYER1)
                .created(LocalDateTime.now())
                .field(field2)
                .build());
    }

    @Test
    void GameService_getById_ReturnGameById() {
        Long id = 1L;

        when(gameRepository.findById(id))
                .thenReturn(Optional.of(testList.get(0)));
        Game game = gameService.getById(id);

        assertNotNull(game);
        assertEquals(testList.get(0), game);
    }

    @Test
    void  GameService_save_CreatedGame() {
        List<Player> players = getPlayerList();
        Long id = 4L;
        Game game = Game.builder()
                .id(id)
                .status(GameStatus.IN_PROGRESS)
                .players(players)
                .build();

        when(gameRepository.findById(id))
                .thenReturn(Optional.of(game));
        when(gameRepository.save(game))
                .thenReturn(game);

        assertNotNull(gameService.save(game));
    }

    @Test
    void  GameService_update_UpdatedGame() {
        Long id = 1L;
        String field = "?~?~? ?~x~? ?~?~?";
        Game fetched = testList.get(0);
        Game game = Game.builder()
                .players(fetched.getPlayers())
                .status(fetched.getStatus())
                .created(fetched.getCreated())
                .field(field)
                .build();

        when(gameRepository.findById(id))
                .thenReturn(Optional.of(fetched));
        when(gameRepository.save(game))
                .thenReturn(game);

        Game saved = gameService.update(id, game);

        assertNotNull(saved);
        assertEquals(id, saved.getId());
        assertEquals(fetched.getCreated(), saved.getCreated());
    }

    private List<Player> getPlayerList() {
        List<Player> players = new ArrayList<>();
        players.add(Player.builder()
                .id(1L)
                .username("Alex")
                .games(testList)
                .email("Alex@gmail.com")
                .build());
        players.add(Player.builder()
                .id(2L)
                .username("Max")
                .games(testList)
                .email("Max@gmail.com")
                .build());
        return players;
    }
}

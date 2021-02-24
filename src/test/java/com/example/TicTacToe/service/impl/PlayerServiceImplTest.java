package com.example.TicTacToe.service.impl;

import com.example.TicTacToe.model.Game;
import com.example.TicTacToe.model.GameStatus;
import com.example.TicTacToe.model.Player;
import com.example.TicTacToe.repository.PlayerRepository;
import com.example.TicTacToe.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

public class PlayerServiceImplTest extends BaseTest {

    @InjectMocks
    private PlayerServiceImpl playerService;

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private GameService gameService;

    private List<Player> testList;

    @BeforeEach
    void setUp(){
        prepareTestData();
    }

    private void prepareTestData() {
        testList = new ArrayList<>();
        List<Game> games = new ArrayList<>();
        testList.add(Player.builder()
                .id(1L)
                .username("Alex")
                .email("Alex@gmail.com")
                .games(games)
                .created(LocalDateTime.now())
                .build());
        testList.add(Player.builder()
                .id(2L)
                .username("Max")
                .email("Max@gmail.com")
                .games(games)
                .created(LocalDateTime.now())
                .build());
    }

    @Test
    void PlayerService_getById_ReturnPlayerById() {
        Long id = 1L;

        when(playerRepository.findById(id))
                .thenReturn(Optional.of(testList.get(0)));
        Player player = playerService.getById(id);

        assertNotNull(player);
        assertEquals(testList.get(0), player);
    }

    @Test
    void PlayerService_getList_ReturnPlayerList() {
        int pageSize = 10;
        int pageNumber = 0;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        when(playerRepository.findAll(pageable))
                .thenReturn( new PageImpl<>(testList));
        Page<Player> page = playerService.getList(pageable);

        assertNotNull(page);
        assertEquals(testList.size(), page.getTotalElements());
    }

    @Test
    void PlayerService_getSortList_ReturnPlayerSortList() {
        int pageSize = 10;
        int pageNumber = 0;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("countWins").descending());

        when(playerRepository.findAll(pageable))
                .thenReturn( new PageImpl<>(testList));
        Page<Player> page = playerService.getList(pageable);

        assertNotNull(page);
        assertEquals(testList.size(), page.getTotalElements());
    }

    @Test
    void PlayerService_getByEmail_ReturnPlayerByEmail() {
        String email = "Alex@gmail.com";

        when(playerRepository.findByEmail(email))
                .thenReturn(testList.get(0));
        Player player = playerService.getByEmail(email);

        assertNotNull(player);
        assertEquals(testList.get(0), player);
    }

    @Test
    void PlayerService_save_CreatedPlayer() {
        List<Game> games = getGameList();
        Long id = 4L;
        Player player = Player.builder()
                .id(id)
                .username("Anna")
                .password("12345")
                .email("Anna@gmail.com")
                .games(games)
                .build();

        when(playerRepository.save(player))
                .thenReturn(player);

        assertNotNull(playerService.save(player));
    }

    @Test
    void PlayerService_update_UpdatedPlayer() {
        Long id = 1L;
        Player fetched = testList.get(0);
        Player player = Player.builder()
                .id(fetched.getId())
                .username("Tina")
                .email("")
                .password("")
                .games(fetched.getGames())
                .build();

        when(playerRepository.findById(id))
                .thenReturn(Optional.of(fetched));
        when(playerRepository.save(player))
                .thenReturn(player);

        Player saved = playerService.update(id, player);

        assertNotNull(saved);
        assertEquals(id, saved.getId());
        assertEquals(fetched.getCreated(), saved.getCreated());
    }

    @Test
    void PlayerService_delete_DeletedPlayer() {
        Long id = 2L;
        Player player = testList.get(1);
        int size = testList.size();
         when(playerRepository.findById(id))
                 .thenReturn(Optional.of(player));
         doAnswer(invocationOnMock -> {
             testList.remove(1);
             return null;
         }).when(playerRepository).deleteById(id);

         playerService.delete(id);

         assertEquals(size-1, testList.size());
    }

    private List<Game> getGameList(){
        List<Game> games = new ArrayList<>();
        String field1 = "?~?~? ?~?~? ?~?~?";
        String field2 = "x~x~x ?~0~? ?~0~?";

        games.add(Game.builder()
                .id(1L)
                .status(GameStatus.IN_PROGRESS)
                .created(LocalDateTime.now())
                .field(field1)
                .build());
        games.add(Game.builder()
                .id(2L)
                .status(GameStatus.WON_PLAYER1)
                .created(LocalDateTime.now())
                .field(field2)
                .build());
        return  games;
    }
}

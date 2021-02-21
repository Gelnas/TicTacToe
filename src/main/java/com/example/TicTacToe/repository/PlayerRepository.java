package com.example.TicTacToe.repository;

import com.example.TicTacToe.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
//    @Query(value = "select * from player_game " +
//            "join player on  player_game.player_id = player.id" +
//            "join game on player_game.game_id = game.id" +
//            "where" +
//            "player_game.player_id = 2", nativeQuery = true)

    //@Query("select h from People h where h.human=?1")
    //value = "select fruit_table.fruit_name, provider_table.provider_name from  fruit_table  join provider_table on fruit_table.provider_code = provider_table.id_provider",  //по идее эту портянку надо засунуть в какой нибудь  Enum
        //        nativeQuery = true
    Player findByEmail(String email);
}

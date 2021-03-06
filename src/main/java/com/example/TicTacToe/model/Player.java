package com.example.TicTacToe.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Entity for the player
 */
@Entity
@Table(name = "player")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    /**
     * Unique identifier of the player
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the new player
     */
    @Column(name = "username")
    private String username;

    /**
     * Player's password
     */
    @Column(name = "password")
    private String password;

    /**
     * The email of the player
     */
    @Column(name = "email")
    private String email;

    /**
     * List of games
     */
    @ManyToMany
    @JsonManagedReference
    @JoinTable(name = "player_game",
            joinColumns = {@JoinColumn(name = "player_id")},
            inverseJoinColumns = {@JoinColumn(name = "game_id")})
    private List<Game> games;

    /**
     * Count of player wins
     */
    @Column(name = "count_wins")
    private Integer countWins;

    /**
     * Count of player defeat
     */
    @Column(name = "count_defeat")
    private Integer countDefeat;

    /**
     * Count of games in a draw
     */
    @Column(name = "count_draw")
    private Integer countDraw;

    @ManyToMany
    @JoinTable(name = "player_roles",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    /**
     * Time of object creation
     */
    @CreationTimestamp
    @Column(name = "created")
    private LocalDateTime created;

    /**
     * Update time
     */
    @UpdateTimestamp
    @Column(name = "updated")
    private LocalDateTime updated;
}

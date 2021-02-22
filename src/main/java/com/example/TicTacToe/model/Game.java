package com.example.TicTacToe.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity for the game
 */
@Entity
@Table(name = "game")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    /**
     * Unique identifier of the user
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Information about game status
     */
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private GameStatus status;

    /**
     * Playing field
     */
    @Column(name = "field")
    private String field;

    /**
     * Information about players
     */
    @ManyToMany(mappedBy = "games")
    @JsonBackReference
    private List<Player> players;

    /**
     * History of moves
     */
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistoryMoves> historyMoves = new ArrayList<HistoryMoves>();

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

    public void addMove(HistoryMoves move) {
        historyMoves.add(move);
        move.setGame(this);
    }
    public void removeMove(HistoryMoves move) {
        historyMoves.remove(move);
        move.setGame(null);
    }
}

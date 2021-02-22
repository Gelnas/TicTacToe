package com.example.TicTacToe.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity for the history of moves in the game
 */
@Entity
@Table(name = "history_moves")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryMoves {

    /**
     * Unique identifier of the user
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Number of move
     */
    @Column(name = "number")
    private int number;

    /**
     * Move position
     */
    @Column(name = "position")
    private String position;

    /**
     * The symbol used during the move
     */
    @Column(name = "symbol")
    private String symbol;

    /**
     * Game
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    private Game game;
}

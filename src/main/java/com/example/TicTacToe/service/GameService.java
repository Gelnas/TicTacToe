package com.example.TicTacToe.service;

import com.example.TicTacToe.exception.NotFoundException;
import com.example.TicTacToe.model.Game;
import com.example.TicTacToe.model.Player;

public interface GameService {

    /**
     * Method for finding a {@link Game game} by its unique identifier.
     * The input argument should not be null.
     *
     * @param id {@link Game game} unique identifier
     * @return {@link Game game} object with unique identifier like in the argument
     * @throws IllegalArgumentException if an input id is null
     * @throws NotFoundException        if there is no {@link Game game} object
     *                                  with unique identifier like in the argument
     */
    Game getById(Long id);

    /**
     * Method for saving a {@link Game game}  in a repository.
     * Input arguments should not be null,
     * otherwise will be thrown IllegalArgumentException.
     *
     * @param game {@link Game game}  object to save
     * @return saved {@link Game game} object
     * @throws IllegalArgumentException if an input {@link Game game} object is null
     */
    Game save(Game game);

    /**
     * Method for updating a  {@link Game game} .
     * Input arguments should not be null,
     * otherwise will be thrown IllegalArgumentException.
     *
     * @param id  {@link Game game} unique identifier
     * @param game {@link Game game} object to update
     * @return updated {@link Game game}  object
     * @throws IllegalArgumentException if any of input arguments is null
     * @throws NotFoundException        if there is no {@link Game game}  object
     *                                  with unique identifier like in the argument
     *                                  is not found
     */
    Game update(Long id,Game game);

    /**
     * Method for converting a field to a string
     * Input arguments should not be null.
     *
     * @param field  {@link Game game} the playing field in the form of an array
     * @return String {@link Game game}  the playing field in the form of a string
     * @throws IllegalArgumentException if any of input arguments is null
     */
    String toString(String[][] field);

    /**
     * Method for converting a field to a array
     * Input arguments should not be null.
     *
     * @param field  {@link Game game} the playing field in the form of  a string
     * @return String {@link Game game}  the playing field in the form of an array
     * @throws IllegalArgumentException if any of input arguments is null
     */
    String[][] toStringArray(String field);

}

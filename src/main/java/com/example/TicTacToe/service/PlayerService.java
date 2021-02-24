package com.example.TicTacToe.service;

import com.example.TicTacToe.exception.NotFoundException;
import com.example.TicTacToe.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlayerService {

    /**
     * Method for finding a {@link Player player} by its email.
     * The input argument should not be null.
     *
     * @param email {@link Player player} email
     * @return {@link Player player} object with unique email like in the argument
     * @throws IllegalArgumentException if an input id is null
     * @throws NotFoundException        if there is no {@link Player player} object
     *                                  with unique email like in the argument
     */
    Player getByEmail(String email);

    /**
     * Method for finding a {@link Player player} by its unique identifier.
     * The input argument should not be null.
     *
     * @param id {@link Player player} unique identifier
     * @return {@link Player player} object with unique identifier like in the argument
     * @throws IllegalArgumentException if an input id is null
     * @throws NotFoundException        if there is no {@link Player player} object
     *                                  with unique identifier like in the argument
     */
    Player getById(Long id);

    /**
     * Method for getting a sorted page of {@link Player player} .
     *
     * @return a page of {@link Player player}
     */
    Page<Player> getSortList();

    /**
     * Method for getting a page of {@link Player player} .
     *
     * @param pageable parameters of requested page
     * @return a sorted page of {@link Player player}
     */
    Page<Player> getList(Pageable pageable);

    /**
     * Method for saving a {@link Player player}  in a repository.
     * Input arguments should not be null,
     * otherwise will be thrown IllegalArgumentException.
     *
     * @param player {@link Player player}  object to save
     * @return saved {@link Player player}  object
     * @throws IllegalArgumentException if an input {@link Player player}  object is null
     */
    Player save(Player player);

    /**
     * Method for updating a  {@link Player player}.
     * Input arguments should not be null,
     * otherwise will be thrown IllegalArgumentException.
     *
     * @param id  {@link Player player} unique identifier
     * @param player {@link Player player} object to update
     * @return updated {@link Player player} object
     * @throws IllegalArgumentException if any of input arguments is null
     * @throws NotFoundException        if there is no {@link Player player} object
     *                                  with unique identifier like in the argument
     *                                  is not found
     */
    Player update(Long id,Player player);

    /**
     * Method for deleting a {@link Player player}.
     * The input argument should not be null,
     * otherwise will be thrown IllegalArgumentException.
     *
     * @param id {@link Player player} unique identifier
     * @throws IllegalArgumentException if an input id is null
     * @throws NotFoundException        if there is no {@link Player player} object
     *                                  with unique identifier like in the argument
     *                                  is not found
     */
    void delete(Long id);
}

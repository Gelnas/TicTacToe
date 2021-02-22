package com.example.TicTacToe.service;

import com.example.TicTacToe.exception.NotFoundException;
import com.example.TicTacToe.model.Role;

public interface RoleService {

    /**
     * Method for finding a {@link Role role} by its unique identifier.
     * The input argument should not be null.
     *
     * @param id {@link Role role}unique identifier
     * @return {@link Role role} object with unique identifier like in the argument
     * @throws IllegalArgumentException if an input id is null
     * @throws NotFoundException        if there is no {@link Role role} object
     *                                  with unique identifier like in the argument
     */
    Role getById(Long id);
}

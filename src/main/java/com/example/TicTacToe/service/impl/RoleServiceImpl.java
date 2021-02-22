package com.example.TicTacToe.service.impl;

import com.example.TicTacToe.exception.ErrorMessages;
import com.example.TicTacToe.exception.NotFoundException;
import com.example.TicTacToe.model.Role;
import com.example.TicTacToe.repository.RoleRepository;
import com.example.TicTacToe.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getById(Long id) {
        Assert.notNull(id, ErrorMessages.NULL_ID.getErrorMessage());

        log.info("Requested the role with id: {}", id);
        return roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found the role with id " + id));
    }
}

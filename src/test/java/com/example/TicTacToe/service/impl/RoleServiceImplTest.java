package com.example.TicTacToe.service.impl;

import com.example.TicTacToe.model.Role;
import com.example.TicTacToe.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class RoleServiceImplTest extends BaseTest{

    @InjectMocks
    RoleServiceImpl roleService;

    @Mock
    RoleRepository roleRepository;

    private Role testRole;

    @BeforeEach
    void setUp(){
        prepareTestData();
    }

    private void prepareTestData() {
        testRole = Role.builder()
                .id(1L)
                .role("USER")
                .build();
    }


    @Test
    void RoleService_getById_returnRoleById() {
        Long id = 1L;

        when(roleRepository.findById(id))
                .thenReturn(Optional.of(testRole));

        Role role = roleService.getById(id);

        assertNotNull(role);
        assertEquals(testRole, role);
    }
}

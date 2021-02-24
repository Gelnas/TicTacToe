package com.example.TicTacToe.mapper.dto;

import com.example.TicTacToe.dto.request.CreatePlayerRequest;
import com.example.TicTacToe.model.Player;
import com.example.TicTacToe.model.Role;
import com.example.TicTacToe.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
public class CreatePlayerRequestToPlayerConverter implements Converter<CreatePlayerRequest, Player> {
    private final RoleService roleService;

    @Override
    public Player convert(CreatePlayerRequest playerRequest) {
        Collection<Role> roles = getRoles(playerRequest.getRole());

        return Player.builder()
                .username(playerRequest.getName())
                .email(playerRequest.getEmail())
                .password(playerRequest.getPassword())
                .countDefeat(0)
                .countWins(0)
                .countDraw(0)
                .roles(roles)
                .build();
    }

    private Collection<Role> getRoles(String role){
        Collection<Role> roles = new ArrayList<Role>();

        if (role.equals("USER")){
            roles.add(roleService.getById(1L));
        }
        if (role.equals("ADMIN")){
            roles.add(roleService.getById(2L));
        }
        return roles;
    }
}
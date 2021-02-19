package com.example.TicTacToe.service.impl;

import com.example.TicTacToe.exception.ErrorMessages;
import com.example.TicTacToe.exception.NotFoundException;
import com.example.TicTacToe.model.Player;
import com.example.TicTacToe.model.Role;
import com.example.TicTacToe.repository.PlayerRepository;
import com.example.TicTacToe.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService, UserDetailsService {
    private final PlayerRepository playerRepository;

    @Transactional(readOnly = true)
    @Override
    @NonNull
    public Player getById(@NonNull Long id) {
        Assert.notNull(id, ErrorMessages.NULL_ID.getErrorMessage());

        log.info("Requested the player with id: {}", id);
        return playerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found the player with id " + id));
    }
    @Transactional(readOnly = true)
    @Override
    @NonNull
    public Page<Player> getList(Pageable pageable) {
        log.info("Requested player page: {} page, {} size",
                pageable.getPageNumber(), pageable.getPageSize());
        return playerRepository.findAll(pageable);
    }

     @Transactional
     @Override
     @NonNull
    public Player save(Player player) {
         Assert.notNull(player, ErrorMessages.NULL_PLAYER_OBJECT.getErrorMessage());
         player.setPassword(passwordEncoder().encode(player.getPassword()));
         Player saved = playerRepository.save(player);
         log.info("Saved a new player with id: {}", saved.getId());
         return saved;
    }

    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(4);
    }

    @Transactional
    @Override
    @NonNull
    public Player update(Long id, Player player) {
        Assert.notNull(id, ErrorMessages.NULL_ID.getErrorMessage());
        Assert.notNull(player, ErrorMessages.NULL_PLAYER_OBJECT.getErrorMessage());

        Player fetched = getById(id);
        player.setId(fetched.getId());
        player.setCreated(fetched.getCreated());

        Player updated = playerRepository.save(player);
        log.info("Updated the player with id: {}", updated.getId());
        return updated;
    }

    @Override
    public void delete(Long id) {
        Assert.notNull(id, ErrorMessages.NULL_ID.getErrorMessage());

        getById(id);
        playerRepository.deleteById(id);
        log.info("The player with id: {}", id, "deleted");
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Player player = findByEmail(email);

        if(isEmpty(player)){
            throw new UsernameNotFoundException("Player not found");
        }
        return new User(player.getEmail(), player.getPassword(), mapRolesToPermission(player.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToPermission(Collection<Role> roles){
        return roles.stream()
                .flatMap(role -> role.getPermissions().stream())
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
    }

    private Player findByEmail(String email){
        log.info("Requested the player with email: {}", email);
        return playerRepository.findByEmail(email);
    }
}

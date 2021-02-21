package com.example.TicTacToe.service.impl;

import com.example.TicTacToe.exception.ErrorMessages;
import com.example.TicTacToe.exception.NotFoundException;
import com.example.TicTacToe.exception.WrongMoveException;
import com.example.TicTacToe.model.Game;
import com.example.TicTacToe.model.GameStatus;
import com.example.TicTacToe.repository.GameRepository;
import com.example.TicTacToe.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Slf4j
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    @Transactional(readOnly = true)
    @Override
    @NonNull
    public Game getById(@NonNull Long id) {
        Assert.notNull(id, ErrorMessages.NULL_ID.getErrorMessage());

        log.info("Requested the game with id: {}", id);
        return gameRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found the game with id " + id));
    }

    @Transactional
    @Override
    @NonNull
    public Game save(Game game) {
        Assert.notNull(game, ErrorMessages.NULL_GAME_OBJECT.getErrorMessage());

        Game saved = gameRepository.save(game);
        log.info("Created a new game with id: {}", saved.getId());
        return game;
    }

    @Transactional
    @Override
    @NonNull
    public Game update(Long id, Game game) {
        Assert.notNull(id, ErrorMessages.NULL_ID.getErrorMessage());
        Assert.notNull(game, ErrorMessages.NULL_GAME_OBJECT.getErrorMessage());

        Game fetched = getById(id);
        if(fetched.getField() == game.getField()){
            throw new WrongMoveException("This cell is already occupied");
        } else {
            game.setStatus(getStatus(game.getField()));
        }
        game.setId(fetched.getId());
        game.setCreated(fetched.getCreated());
        game.setPlayers(fetched.getPlayers());
        Game updated = gameRepository.save(game);
        log.info("Updated the game with id: {}", updated.getId());
        return updated;
    }

    private GameStatus getStatus(String field){
        String[][] fieldArray = toStringArray(field);
        if(fieldArray[0][0].equals("x") && fieldArray[1][1].equals("x") && fieldArray[2][2].equals("x") ||
                fieldArray[0][2].equals("x") && fieldArray[1][1].equals("x") && fieldArray[2][0].equals("x")) {
            return GameStatus.WON_PLAYER1;
        }
        if(fieldArray[0][0].equals("0") && fieldArray[1][1].equals("0") && fieldArray[2][2].equals("0") ||
                fieldArray[0][2].equals("0") && fieldArray[1][1].equals("0") && fieldArray[2][0].equals("0")){
            return GameStatus.WON_PLAYER2;
        }
        for(int i = 0; i < 3; i++){
            if(fieldArray[i][0].equals("x") && fieldArray[i][1].equals("x") && fieldArray[i][2].equals("x") ||
                    fieldArray[0][i].equals("x") && fieldArray[1][i].equals("x") && fieldArray[2][i].equals("x")){
                return GameStatus.WON_PLAYER1;
            }
            if(fieldArray[i][0].equals("0") && fieldArray[i][1].equals("0") && fieldArray[i][2].equals("0") ||
                    fieldArray[0][i].equals("0") && fieldArray[1][i].equals("0") && fieldArray[2][i].equals("0")){
                return GameStatus.WON_PLAYER2;
            }
        }
        for(int i = 0; i < 3; i ++){
            for (int j = 0; j < 3; j++){
                if (fieldArray[i][j].equals("?")){
                    return GameStatus.IN_PROGRESS;
                }
            }
        }
        return GameStatus.DRAW;
    }

    @Override
    public String[][] toStringArray(String field){
        String[][] result = new String[3][3];
        String[] line = field.split(" ");
        String[] inter;
        for (int i = 0; i < 3; i++){
            inter = line[i].split("~");
            for (int j = 0; j < 3; j++) {
                result[i][j] = inter[j];
            }
        }
        return result;
    }

    @Override
    public String toString(String[][] field){
        String result = "";
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++) {
                result = j != 2 ?  result + field[i][j] + "~" : result + field[i][j];
            }
            result = i != 2 ?  result + " " : result;
        }
        return result;
    }

}

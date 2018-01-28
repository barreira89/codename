package com.stevebarreira.codename.model.transformer.impl;

import com.stevebarreira.codename.model.GameBoards;
import com.stevebarreira.codename.model.GameRound;
import com.stevebarreira.codename.model.Games;
import com.stevebarreira.codename.model.dto.GamesDto;
import com.stevebarreira.codename.model.transformer.Transformer;

import java.util.*;
import java.util.stream.Collectors;

public class GamesTransformer implements Transformer<Games, GamesDto>{
    @Override
    public GamesDto transform(Games input) {
        GamesDto gamesDto = new GamesDto();
        gamesDto.setId(input.id);
        gamesDto.setGameRounds(input.getRounds());
        gamesDto.setGameBoards(getGameRoundsFromRounds(input.getRounds()));
        return gamesDto;
    }

    private List<GameBoards> getGameRoundsFromRounds(List<GameRound> gameRoundsList) {
        List<GameBoards> gameBoards = new ArrayList<>();
        if(gameRoundsList != null){
            gameBoards = gameRoundsList.stream()
                    .map(GameRound::getGameBoard)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
        return gameBoards;
    }
}

package com.stevebarreira.codename.model.transformer.impl;

import com.stevebarreira.codename.model.GameBoards;
import com.stevebarreira.codename.model.GameRound;
import com.stevebarreira.codename.model.Games;
import com.stevebarreira.codename.model.dto.GamesDto;
import com.stevebarreira.codename.model.transformer.Transformer;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GamesTransformer implements Transformer<Games, GamesDto>{
    @Override
    public GamesDto transform(Games input) {
        return new GamesDto(
                input.getId(),
                null,
                input.getRounds(),
                getGameRoundsFromRounds(input.getRounds())
        );
    }

    private List<GameBoards> getGameRoundsFromRounds(List<GameRound> gameRoundsList) {
        return Optional.ofNullable(gameRoundsList)
                .map(Collection::stream)
                .orElse(Stream.empty())
                .map(GameRound::getGameBoard)
                .collect(Collectors.toList());
    }
}

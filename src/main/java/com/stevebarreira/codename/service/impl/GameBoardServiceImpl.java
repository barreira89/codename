package com.stevebarreira.codename.service.impl;

import com.stevebarreira.codename.model.*;
import com.stevebarreira.codename.repository.GameBoardRepository;
import com.stevebarreira.codename.service.GameBoardService;
import com.stevebarreira.codename.service.WordListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class GameBoardServiceImpl implements GameBoardService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameBoardServiceImpl.class);

    @Autowired
    private GameBoardRepository gameBoardRepository;

    @Autowired
    private WordListService wordListService;

    @Override
    public GameBoards createRandomGameBoard() throws RuntimeException {
        WordList wordList = wordListService.getRandomWordList();
        TeamList teamList = new TeamList();

        Optional.ofNullable(wordList)
                .orElseThrow(() -> new RuntimeException("No WordList Available, Default Failure"));

        return gameBoardRepository.save(setupGameBoard(wordList, teamList));
    }

    private GameBoards setupGameBoard(WordList wordList, TeamList teamList) {
        return new GameBoards(
                teamList,
                wordList,
                AssignTeamService.getAssignedTeams(teamList, wordList)
        );
    }

    @Override
    public List<GameBoards> getAllGameBoards() {
        return gameBoardRepository.findAll();
    }

    @Override
    public GameBoards getGameBoardById(String id) {
        return gameBoardRepository.findOne(id);
    }

    @Override
    public GameBoards updateGameBoard(GameBoards gameBoard) {
        return gameBoardRepository.save(gameBoard);
    }

}

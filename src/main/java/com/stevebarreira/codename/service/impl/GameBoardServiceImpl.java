package com.stevebarreira.codename.service.impl;

import com.stevebarreira.codename.exception.EntityNotFoundException;
import com.stevebarreira.codename.model.GameBoards;
import com.stevebarreira.codename.model.GameRow;
import com.stevebarreira.codename.model.TeamList;
import com.stevebarreira.codename.model.WordList;
import com.stevebarreira.codename.repository.GameBoardRepository;
import com.stevebarreira.codename.service.AssignTeamService;
import com.stevebarreira.codename.service.GameBoardService;
import com.stevebarreira.codename.service.WordListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameBoardServiceImpl implements GameBoardService {

    @Autowired
    private GameBoardRepository gameBoardRepository;

    @Autowired
    private WordListService wordListService;

    @Autowired
    private AssignTeamService assignTeamService;

    @Override
    public GameBoards createRandomGameBoard() throws RuntimeException {
        WordList wordList = wordListService.getRandomWordList();
        TeamList teamList = new TeamList();

        Optional.ofNullable(wordList)
                .orElseThrow(() -> new RuntimeException("No WordList Available, Default Failure"));

        return gameBoardRepository.save(setupGameBoard(wordList, teamList));
    }

    @Override
    public List<GameBoards> getAllGameBoards() {
        return gameBoardRepository.findAll();
    }

    @Override
    public GameBoards getGameBoardById(String id) {
        return gameBoardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No GameBoards"));
    }

    @Override
    public GameBoards updateGameBoard(GameBoards gameBoard) {
        return gameBoardRepository.save(gameBoard);
    }

    private GameBoards setupGameBoard(WordList wordList, TeamList teamList) {
        List<GameRow> gameRows = assignTeamService.getAssignedTeams(teamList, wordList);
        return new GameBoards(teamList, wordList, gameRows);
    }

}

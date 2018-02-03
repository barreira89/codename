package com.stevebarreira.codename.model.validator.impl;

import com.stevebarreira.codename.model.GameClue;
import com.stevebarreira.codename.model.validator.Validator;
import org.springframework.stereotype.Service;

@Service
public class ClueValidator implements Validator<GameClue> {

    public boolean isValid(GameClue input) {
        return containsRequiredFields(input);
    }

    private boolean containsRequiredFields(GameClue input){
        return (input.getClue() != null && input.getNumberOfWords() != null && input.getTeam() != null);

    }
}

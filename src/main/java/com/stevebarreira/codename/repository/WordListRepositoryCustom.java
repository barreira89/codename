package com.stevebarreira.codename.repository;

import com.stevebarreira.codename.model.dto.Codenames;

public interface WordListRepositoryCustom {

    Codenames findRandomCodeName();
}

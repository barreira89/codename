package com.stevebarreira.codename.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id

class GameTile {

    @Id
    @JsonIgnore
    String id
    Word word
    Team team

    int rowIndex
    int colIndex
    boolean selected
}

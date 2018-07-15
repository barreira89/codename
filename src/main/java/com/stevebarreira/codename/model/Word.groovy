package com.stevebarreira.codename.model

class Word {
    String value
    boolean selected

    Word(String value){
        this.value = value
    }

    Word selectWord(){
        this.selected = true
        return this;
    }
}

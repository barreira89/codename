package com.stevebarreira.codename.model

import spock.lang.Specification

class WordListSpec extends Specification {

    def "Word List Size"() {
        given:
        def wordList = new WordList();

        expect:
        wordList.getStringWords().size() == 25;
    }

    def "Get Random Word"() {
        given:
        WordList wordList = new WordList()

        when:
        Word randomSelectedWord = wordList.getRandomWord()

        then:
        wordList.getStringWords().size() == 25;
        println(randomSelectedWord.value)
        wordList.words.find { it.selected } == randomSelectedWord
        //wordList.selectWordList.size() == wordList.getStringWords().size() - 1

    }

    def "Use up Select List"() {
        given:
        def wordList = new WordList()
        25.times {
            wordList.getRandomWord();
        }
        expect:
        wordList.getStringWords().size() == 25;

    }

    def "Over use Select List"() {
        given:
        def wordList = new WordList()
        26.times {
            wordList.getRandomWord();
        }
        expect:
        wordList.getStringWords().size() == 25;
    }

    def "Empty List"() {
        given:
        def emptyList = new ArrayList<String>()
        def wordList = new WordList(emptyList)
        def emptyValue = wordList.getRandomWord();

        expect:
        wordList.getStringWords().size() == 0;
        wordList.selectWordList.size() == 0;
        emptyValue == "EMPTYLIST"

    }

}

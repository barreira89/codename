package com.stevebarreira.codename.service

import com.stevebarreira.codename.model.WordList
import com.stevebarreira.codename.model.dto.Codenames
import com.stevebarreira.codename.repository.WordlistRepository
import com.stevebarreira.codename.service.impl.WordListServiceImpl
import spock.lang.Specification

class WordListServiceSpec extends Specification {

    WordlistRepository mockWordListRepository = Mock()
    Random mockGenerator = new Random()

    WordListService wordListService = new WordListServiceImpl(
            wordListRepository: mockWordListRepository,
            generator: mockGenerator
    )

    def wordListOfSize(size) {
        def array = new ArrayList<String>()
        size.times { array << "TEST" + it }
        return array
    }

    def 'getRandomWordList - results from repo'() {
        when:
        WordList resultWordList = wordListService.getRandomWordList()

        then:
        1 * mockWordListRepository.findAll() >> [new Codenames(words: wordListOfSize(listSizeInput)),
                                                 new Codenames(words: wordListOfSize(listSizeInput + 3))
        ]
        0 * _

        and:
        resultWordList
        resultWordList.wordList
        resultWordList.wordList.size() == listSizeResult

        where:
        listSizeInput || listSizeResult
        25            || 25
        600           || 25
    }

    def 'getRandomWordList - no results from repo - uses default output'() {
        when:
        WordList resultWordList = wordListService.getRandomWordList()

        then:
        1 * mockWordListRepository.findAll() >> []

        and:
        resultWordList
        resultWordList.wordList
        resultWordList.wordList.size() == WordList.DEFAULT_LIST_SIZE

    }

    def 'getRandomWordList - Error Scenarios'() {
        when:
        WordList resultWordList = wordListService.getRandomWordList()

        then:
        1 * mockWordListRepository.findAll() >> [new Codenames(words: wordListOfSize(20))]
        thrown(IllegalArgumentException)

//		and:
//
//		!resultWordList
    }
}

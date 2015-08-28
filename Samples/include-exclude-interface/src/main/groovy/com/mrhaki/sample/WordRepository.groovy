package com.mrhaki.spock

import java.util.stream.Collectors

class WordRepository {

    private final List<String> words =
            ['Groovy', 'Gradle', 'Griffon', 'Grails', 'Spock']

    List<String> findWords(final String startingWithLetter) {
        words
                .stream()
                .filter { word -> word.startsWith(startingWithLetter) }
                .collect(Collectors.toList())
    }

}

package com.mrhaki.spock

import spock.lang.Specification
import spock.lang.Subject

abstract class WordRepositorySpec<S extends Access> extends Specification {

    @Subject
    S access

    def "test remote or local access"() {
        expect:
        access.findWords('S') == ['Spock']
    }

}
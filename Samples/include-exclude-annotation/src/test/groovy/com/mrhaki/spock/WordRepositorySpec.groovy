package com.mrhaki.spock

import spock.lang.Specification

class WordRepositorySpec extends Specification {

    @Remote  // Apply our Remote annotation.
    def "test remote access"() {
        given:
        final RemoteAccess access = new RemoteAccess()

        expect:
        access.findWords('S') == ['Spock']
    }

    def "test local access"() {
        given:
        final LocalAccess access = new LocalAccess()

        expect:
        access.findWords('S') == ['Spock']
    }

}
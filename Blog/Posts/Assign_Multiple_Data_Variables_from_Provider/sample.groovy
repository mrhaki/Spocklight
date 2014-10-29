package com.mrhaki.spock

@Grab('org.spockframework:spock-core:0.7-groovy-2.0')
import spock.lang.*

class MultiDataVarSpec extends Specification {

    @Unroll("#value as upper case is #expected")
    def "check upper case value of String"() {
        expect:
        value.toUpperCase() == expected

        where:
        // Multi data variables value and expected,
        // will be filled with elements from a row
        // on each iteration. The first element of each
        // row is ignored.
        // E.g. on first iteration:
        // value = 'abc'
        // and expected = 'ABC'
        [_, value, expected] &lt;&lt; [
            [1, 'abc', 'ABC'],
            [2, 'def', 'DEF'], 
            [3, '123', '123']
        ]
    }


}

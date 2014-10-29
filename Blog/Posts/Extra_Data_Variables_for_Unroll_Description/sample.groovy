package com.mrhaki.spock

import spock.lang.*

class SampleSpec extends Specification {

    @Unroll
    // Alternatively syntax as 
    // unroll annotation argument:
    // @Unroll("'#value' is #unrollDescription")
    def "'#value' is #unrollDescription"() {
        expect:
        value.every { (it as char).isLowerCase() } == result

        where:
        value || result
        'A'   || false
        'Ab'  || false
        'aB'  || false
        'a'   || true
        'ab'  || true

        unrollDescription = result ? 'lower case' : 'not lower case'
    }

}

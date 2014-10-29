// File: SampleSpecification.groovy
package com.mrhaki.spock

@Grab('org.hamcrest:hamcrest-all:1.3')
import static org.hamcrest.Matchers.*

@Grab('org.spockframework:spock-core:0.7-groovy-2.0')
import static spock.util.matcher.HamcrestSupport.*
import spock.lang.Specification

class SampleSpecification extends Specification {

    def "sample usage of hamcrest matcher hasKey"() {
        when:
        final sampleMap = [name: 'mrhaki']

        then:
        expect sampleMap, hasKey('name')
    }

}

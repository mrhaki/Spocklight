package com.mrhaki.spock

@Grab('org.spockframework:spock-core:0.7-groovy-2.0')
import spock.lang.*

class SampleSpec extends Specification {

    final ClassUnderTest classUnderTest = new ClassUnderTest()

    @Unroll
    def "show #name with start time 21h is #expected to show"() {
        setup:
        final AgeService ageService = Mock()
        classUnderTest.ageService = ageService

        when:
        final boolean allowed = classUnderTest.listing(new Show(name: name, startTime: 21)) 

        then:
        1 * ageService.allowedMaxTime(_ as Show) >> { Show show ->
            show.name.toLowerCase().contains('kids') ? 10 : 23
        }
        // Alternative syntax with a non-typed closure argument:
        //1 * ageService.allowedMaxTime(_ as Show) >> { arguments ->
        //    arguments[0].name.toLowerCase().contains('kids') ? 10 : 23
        //}

        allowed == expected

        where:
        name            || expected
        'Kids rules'    || false
        'Sports united' || true
    }

}

/* Supporting classes and interface */

class ClassUnderTest {

    AgeService ageService

    boolean listing(final Show show) {
        final int hour = ageService.allowedMaxTime(show)
        show.startTime <= hour
    }
}

interface AgeService {
    int allowedMaxTime(Show show)
}

@groovy.transform.Canonical
class Show {
    String name
    int startTime
}

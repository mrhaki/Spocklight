package com.mrhaki.spock

@Grab('org.spockframework:spock-core:0.7-groovy-2.0')
import spock.lang.*

// The @Subject annotation can also be applied at class level.
// We must specify the class or classes as arguments:
// @Subject([Greet])
class GreetSpec extends Specification {

    // The greet variable of type Greet is the 
    // class we are testing in this specification.
    // We indicate this with the @Subject annotation.
    @Subject 
    private Greet greet = new Greet(['Hi', 'Hello'])

    // Simple specification to test the greeting method.
    def "greeting should return a random salutation followed by name"() {
        when:
        final String greeting = greet.greeting('mrhaki')

        then:
        greeting == 'Hi, mrhaki' || greeting == 'Hello, mrhaki'
    }

}

/**
 * Class which is tested in the above specification.
 */
@groovy.transform.Immutable
class Greet {

    final List&lt;String&gt; salutations

    String greeting(final String name) {
        final int numberOfSalutations = salutations.size()
        final int selectedIndex = new Random().nextInt(numberOfSalutations)
        final String salutation = salutations.get(selectedIndex)

        "${salutation}, ${name}"
    }

}

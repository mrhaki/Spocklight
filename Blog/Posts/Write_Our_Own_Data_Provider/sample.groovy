package com.mrhaki.spock

@Grab('org.spockframework:spock-core:0.7-groovy-2.0')
import spock.lang.*

class ProviderSampleSpec extends Specification {

    @Unroll("Gender #gender for #name is #description")
    def "check if user is female or male based on gender value"() {
        given:
        def userData = '''\
            1;mrhaki;M;false
            2;Britt;F;true'''

        expect:
        new User(name: name, gender: gender).female == Boolean.valueOf(expected)

        where:
        [_, name, gender, expected] &lt;&lt; new MultilineProvider(source: userData)

        // Extra data variable to be used in
        // @Unroll description.
        description = expected ? 'female' : 'not female'
    }

}

/**
 * Class under test. 
 */
class User {
    String name, gender

    Boolean isFemale() {
        gender == 'F'
    }
}

/**
 * Class implements Iterable interface so 
 * it can be used as data provider.
 */
class MultilineProvider implements Iterable {
    def source
    def lines
    def separator = ';'

    private int counter

    /**
     * Set multiline String as source 
     * and transform to a List of String
     * values and assign to the lines
     * property.
     */
    void setSource(source) {
        this.source = source.stripIndent()
        lines = this.source.readLines()
    }

    @Override
    Iterator iterator() {
        [
            hasNext: { 
                counter &lt; lines.size() 
            },
            next: { 
                lines[counter++].tokenize(separator)
            }
        ] as Iterator
    }
}

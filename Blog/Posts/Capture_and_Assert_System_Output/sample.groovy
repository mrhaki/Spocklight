package com.mrhaki.spock

@Grab('org.spockframework:spock-core:0.7-groovy-2.0')
import spock.lang.*

@Grab('org.springframework.boot:spring-boot:1.2.1.RELEASE')
import org.springframework.boot.test.OutputCapture

class CaptureOutputSpec extends Specification {

    @org.junit.Rule
    OutputCapture capture = new OutputCapture()


    def "capture output print method"() {
        when:
        print 'Groovy rocks'

        then:
        capture.toString() == 'Groovy rocks'
    }

    def "banner text must contain given messagen and fixed header"() {
        given:
        final Banner banner = new Banner(message: 'Spock is gr8!')

        when:
        banner.print()

        then:
        final List lines = capture.toString().tokenize(System.properties['line.separator'])
        lines.first() == '*** Message ***'
        lines.last()  == ' Spock is gr8! '
    }

}


/**
 * Class under test. The print method
 * uses println statements to display
 * some message on the console.
 */
class Banner {

    String message 

    void print() {
        println ' Message '.center(15, '*')
        println message.center(15)
    }

}

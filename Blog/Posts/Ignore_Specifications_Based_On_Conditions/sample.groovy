package com.mrhaki.spock

import spock.lang.*

class SampleRequiresSpec extends Specification {

    private static boolean isOsWindows() {
        System.properties['os.name'] == 'windows'
    }

    @IgnoreIf({ Boolean.valueOf(properties['spock.ignore.longRunning']) })
    def "run spec if Java system property 'spock.ignore.longRunning' is not set or false"() {
        expect:
        true
    }

    @IgnoreIf({ Boolean.valueOf(env['SPOCK_IGNORE_LONG_RUNNING']) })
    def "run spec if environment variable 'SPOCK_IGNORE_LONG_RUNNING' is not set or false"() {
        expect:
        true
    }

    @IgnoreIf({ javaVersion < 1.7 })
    def "run spec if run in Java 1.7 or higher"() {
        expect:
        true
    }

    @IgnoreIf({ javaVersion != 1.7 })
    def "run spec if run in Java 1.7"() {
        expect:
        true
    }

    @IgnoreIf({ isOsWindows() })
    def "run only if run on non-windows operating system"() {
        expect:
        true
    }

}

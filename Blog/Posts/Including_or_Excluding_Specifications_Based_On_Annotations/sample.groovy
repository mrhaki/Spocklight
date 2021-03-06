apply plugin: 'groovy'

repositories {
    jcenter()
}

dependencies {
    compile 'org.codehaus.groovy:groovy-all:2.4.4'
    testCompile 'org.spockframework:spock-core:1.0-groovy-2.4'
}

// New test task with specific 
// Spock configuration file.
task remoteTest(type: Test) {
    // This task belongs to Verification task group.
    group = 'Verification'

    // Set Spock configuration file when running
    // this test task.
    systemProperty 'spock.configuration', 'RemoteSpockConfig.groovy'
}

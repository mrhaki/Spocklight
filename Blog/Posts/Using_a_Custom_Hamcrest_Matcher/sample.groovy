JUnit 4 Runner, Tests: 1, Failures: 1, Time: 200
Test Failure: sample usage of custom hamcrest matcher(com.mrhaki.spock.SampleSpecification)
Condition not satisfied:

that list, inRange(0..3)
|    |
|    [2, 3, 4]
false

Expected: list be in range [0, 1, 2, 3]
     but: "[2, 3, 4]" was not in range [0, 1, 2, 3]

    at com.mrhaki.spock.SampleSpecification.sample usage of custom hamcrest matcher(SampleSpecification.groovy:18)


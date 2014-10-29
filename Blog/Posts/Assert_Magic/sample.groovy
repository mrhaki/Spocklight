$ groovy CourseServiceSpec.groovy
JUnit 4 Runner, Tests: 1, Failures: 1, Time: 228
Test Failure: Create new course with teacher and description(com.mrhaki.blog.CourseServiceSpec)
Condition not satisfied:

'Grails Goodness' == course.description
                  |  |      |
                  |  |      Groovy Goodness
                  |  com.mrhaki.blog.Course@3435ec9
                  false
                  4 differences (73% similarity)
                  Gr(ails) Goodness
                  Gr(oovy) Goodness

    at com.mrhaki.blog.CourseServiceSpec.assertCourse(CourseServiceSpec.groovy:21)
    at com.mrhaki.blog.CourseServiceSpec.Create new course with teacher and description(CourseServiceSpec.groovy:16)

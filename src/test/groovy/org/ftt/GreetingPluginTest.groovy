package org.ftt

import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import spock.lang.Specification
import spock.lang.TempDir

class GreetingPluginTest extends Specification {

    @TempDir
    File testProjectDir

    File buildFile

    def setup() {
        buildFile = new File(testProjectDir, "build.gradle")
        buildFile << """
            plugins {
                id 'org.ftt.greeting'
            }
        """
    }

    def "can successfully call Greeting task"() {
        when:
        def result = GradleRunner.create()
                .withProjectDir(testProjectDir)
                .withArguments('Greeting')
                .withPluginClasspath()
                .build()
        then:
        result.task(":Greeting").outcome == TaskOutcome.SUCCESS
    }
}

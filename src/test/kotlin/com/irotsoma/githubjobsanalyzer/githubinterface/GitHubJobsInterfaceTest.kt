package com.irotsoma.githubjobsanalyzer.githubinterface

import mu.KLogging
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

/**
 * Simple integration test that checks that the countJobs function returns values when both or either parameter is present with throwing exceptions
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class GitHubJobsInterfaceTest {
    /** kotlin-logging implementation*/
    private companion object: KLogging()

    @Autowired
    lateinit var gitHubJobsInterface: GitHubJobsInterface

    @Test
    fun countJobs() {
        val seattleJava = gitHubJobsInterface.countJobs("java", "Seattle" )
        logger.debug("Test result: $seattleJava Java jobs in Seattle.")
        val allJava = gitHubJobsInterface.countJobs("java", null)
        logger.debug("Test result: $allJava Java jobs in all locations.")
        val allSeattle = gitHubJobsInterface.countJobs(null, "Seattle")
        logger.debug("Test result: $allSeattle All jobs in Seattle.")
    }
}
package com.irotsoma.githubjobsanalyzer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class GithubJobsAnalyzerApplication

fun main(args: Array<String>) {
    runApplication<GithubJobsAnalyzerApplication>(*args)
}

package com.irotsoma.githubjobsanalyzer.githubinterface

import com.irotsoma.githubjobsanalyzer.data.Jobs
import com.irotsoma.githubjobsanalyzer.data.JobsRepository
import com.irotsoma.githubjobsanalyzer.data.LanguageRepository
import com.irotsoma.githubjobsanalyzer.data.LocationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service

/**
 * Service to load the local database with records matching each combination of location and programming language
 *
 * @property jobsRepository Autowired instance of JPA [JobsRepository]
 * @property languageRepository Autowired instance of JPA [LanguageRepository]
 * @property locationRepository Autowired instance of JPA [LocationRepository]
 * @property gitHubJobsInterface Autowired instance of [GitHubJobsInterface] used to query the API
 */
@Service
@Lazy
class LoadAllDataService {
    @Autowired
    lateinit var jobsRepository: JobsRepository

    @Autowired
    lateinit var languageRepository: LanguageRepository

    @Autowired
    lateinit var locationRepository: LocationRepository

    @Autowired
    lateinit var gitHubJobsInterface: GitHubJobsInterface

    /**
     * Loads all relevant records by cycling through each location and language and saves each record to the JobsRepository
     */
    fun loadAll() {
        for (location in locationRepository.findAll()) {
            //first instance without a language to get the total number of jobs for use in percent calculations
            var result = gitHubJobsInterface.countJobs(null, location.name)
            var jobs = Jobs(null, location, result)
            jobsRepository.save(jobs)
            for (language in languageRepository.findAll()) {
                result = gitHubJobsInterface.countJobs(language.name, location.name)
                jobs = Jobs(language, location, result)
                jobsRepository.save(jobs)
            }
        }
    }
}
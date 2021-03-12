package com.irotsoma.githubjobsanalyzer.githubinterface

import com.irotsoma.githubjobsanalyzer.data.JobsRepository
import com.irotsoma.githubjobsanalyzer.data.LanguageRepository
import com.irotsoma.githubjobsanalyzer.data.LocationRepository
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

/**
 * Integration test that load all returns one record for each combination of location and language including null as a language
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class LoadAllDataServiceTest {

    @Autowired
    lateinit var jobsRepository: JobsRepository

    @Autowired
    lateinit var languageRepository: LanguageRepository

    @Autowired
    lateinit var locationRepository: LocationRepository

    @Autowired
    lateinit var loadAllDataService: LoadAllDataService

    @Test
    fun loadAll() {
        loadAllDataService.loadAll()
        assert(jobsRepository.count() == locationRepository.count() * (languageRepository.count() + 1))
    }
}
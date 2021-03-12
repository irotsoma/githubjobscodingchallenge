package com.irotsoma.githubjobsanalyzer.githubinterface

import mu.KLogging
import org.springframework.context.annotation.Lazy
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestClientResponseException
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

/**
 * Interface for accessing the github jobs API and counting jobs by location and/or programming language
 *
 * @property defaultUrl The base url for the api
 * @property jobsPerPage The expected number of jobs per page returned by the api
 */
@Service
@Lazy
class GitHubJobsInterface {
    /** kotlin-logging implementation*/
    private companion object: KLogging()

    private val defaultUrl = "https://jobs.github.com/positions.json"
    private val jobsPerPage = 50

    /**
     * Queries the API with location and/or programming language
     * One or the other of location or programming language is required to be non-null
     *
     * @param searchDescription Programming language with which to search the description
     * @param locationFilter Location to filter records
     * @returns Count of records matching the given criteria
     */
    @Throws(RestClientResponseException::class, RestClientException::class)
    fun countJobs(searchDescription: String?, locationFilter: String?): Int {
        if (searchDescription == null && locationFilter == null){
            logger.error {"Searching for jobs with no filters is not supported."}
            throw UnsupportedOperationException("Searching for jobs with no filters is not supported.")
        }
        //Build uri
        val uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(defaultUrl)
            .queryParam("page", 0)
        //add optional search param (language)
        if (searchDescription != null) {
            uriComponentsBuilder.queryParam("search","$searchDescription")
        }
        //add optional location param
        if (locationFilter!=null){
            uriComponentsBuilder.queryParam("location", locationFilter)
        }
        var jobCount = 0
        var pageCount = 1
        var nextPage = true

        // call the API with the given criteria starting with page 0
        logger.debug { "Calling url:  ${uriComponentsBuilder.toUriString()}" }
        var returnValue = RestTemplate().getForEntity(uriComponentsBuilder.build().toUri(), Array<JobsDTO>::class.java)
        while (returnValue.statusCode == HttpStatus.OK && nextPage && returnValue.body?.isNotEmpty() == true){
            jobCount += returnValue.body!!.size

            if (returnValue.body!!.size < jobsPerPage) { //if the query returned fewer records than the expected jobs per page, then this is the last page of records
                nextPage = false
            } else { //otherwise query again with the next page number
                uriComponentsBuilder.replaceQueryParam("page", pageCount++)
                logger.debug { "Calling url:  ${uriComponentsBuilder.toUriString()}" }
                returnValue = RestTemplate().getForEntity(uriComponentsBuilder.build().toUri() ,Array<JobsDTO>::class.java)
            }

        }
        if (returnValue.statusCode != HttpStatus.OK){
            throw RestClientResponseException("Http error accessing GitHub Jobs API.", returnValue.statusCodeValue, returnValue.statusCode.reasonPhrase, returnValue.headers, null, null)
        }
        return jobCount
    }
}
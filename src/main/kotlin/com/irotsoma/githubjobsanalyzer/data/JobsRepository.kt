package com.irotsoma.githubjobsanalyzer.data

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * JPA repository object for storing Jobs counts
 */
@Repository
interface JobsRepository: JpaRepository<Jobs, Int> {
    /**
     * @return all records ordered by location and language (nulls first)
     */
    fun findAllByOrderByLocationAscLanguageAsc(): List<Jobs>
}
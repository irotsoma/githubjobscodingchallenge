package com.irotsoma.githubjobsanalyzer.data

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * JPA repository object for storing locations to lookup
 */
@Repository
interface LocationRepository: JpaRepository<Location, Int>
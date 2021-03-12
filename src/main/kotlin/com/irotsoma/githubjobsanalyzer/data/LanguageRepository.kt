package com.irotsoma.githubjobsanalyzer.data

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
/**
 * JPA repository object for storing languages to lookup
 */
@Repository
interface LanguageRepository: JpaRepository<Language, Int>
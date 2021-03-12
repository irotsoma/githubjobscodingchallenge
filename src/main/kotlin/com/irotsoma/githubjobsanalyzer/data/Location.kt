package com.irotsoma.githubjobsanalyzer.data

import javax.persistence.*

/**
 * JPA language object
 * Initialized by liquibase script
 *
 * @property id Database-generated ID for the record
 * @property name The name of the location
 */
@Entity
@Table(name="location")
class Location(@Column(name="name", unique = true, insertable = true, updatable = false, nullable = false)
                     val name: String
                     ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1

    /**
     * @return the name of the location
     */
    override fun toString(): String {
        return name
    }
}
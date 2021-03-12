package com.irotsoma.githubjobsanalyzer.data

import javax.persistence.*
import kotlin.jvm.Transient

/**
 * JPA jobs object
 *
 * @property id Database-generated ID for the record
 * @property language Instance of the [Language] JPA object associated with this record.
 * @property location Instance of the [Location] JPA object associated with this record.
 * @property count Count of jobs listed on github jobs with this combination of language and location.
 * @property percentageByLocation Populated by the JobsController and used by the mustache template to populate the percent column.
 */
@Entity
@Table(name="jobs")
class Jobs(@ManyToOne(cascade = [CascadeType.PERSIST])
           @JoinColumn(name="language_id", referencedColumnName = "id")
           val language: Language?,

           @ManyToOne(cascade = [CascadeType.PERSIST])
           @JoinColumn(name="location_id", referencedColumnName = "id")
           val location: Location?,

           @Column (name="count", nullable = false, updatable = true)
           val count: Int
           ) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1

    @Transient
    var percentageByLocation: Int = 0
}
package com.irotsoma.githubjobsanalyzer.githubinterface

import java.io.Serializable

/**
 * POJO for the JSON object returned by the API
 */
class JobsDTO : Serializable {
    var companyLogo: String? = null
    var howToApply: String? = null
    var createdAt: String? = null
    var description: String? = null
    var company: String? = null
    var companyUrl: String? = null
    var location: String? = null
    var id: String? = null
    var type: String? = null
    var title: String? = null
    var url: String? = null

    override fun toString(): String {
        return "JobEntity{" +
                "company_logo = '" + companyLogo + '\'' +
                ",how_to_apply = '" + howToApply + '\'' +
                ",created_at = '" + createdAt + '\'' +
                ",description = '" + description + '\'' +
                ",company = '" + company + '\'' +
                ",company_url = '" + companyUrl + '\'' +
                ",location = '" + location + '\'' +
                ",id = '" + id + '\'' +
                ",type = '" + type + '\'' +
                ",title = '" + title + '\'' +
                ",url = '" + url + '\'' +
                "}"
    }
}
package com.irotsoma.githubjobsanalyzer.webui

import com.irotsoma.githubjobsanalyzer.data.JobsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

/**
 * REST controller for the homepage which just displays a please wait message and then redirects to the /jobs.
 * Can take a parameter "refresh=true" to clear all jobs from the db so they'll be reloaded from the API if using a persistent DB
 *
 * @property jobsRepository Autowired instance of JPA [JobsRepository]
 */
@Controller
class HomeController {
    @Autowired
    lateinit var jobsRepository: JobsRepository

    /**
     * REST GET function for the index
     *
     * @param refresh if set to true, the jobs table is cleared and will be reloaded
     * @return name of mustache template to load
     */
    @GetMapping("/")
    fun home(@RequestParam refresh:Boolean?): String {
        if (refresh == true){
            jobsRepository.deleteAll()
        }
        return "index"
    }
}
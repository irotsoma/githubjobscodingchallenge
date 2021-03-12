package com.irotsoma.githubjobsanalyzer.webui

import com.irotsoma.githubjobsanalyzer.data.JobsRepository
import com.irotsoma.githubjobsanalyzer.githubinterface.LoadAllDataService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import kotlin.math.roundToInt

/**
 * REST controller for the jobs listing page
 *
 * @property jobsRepository Autowired instance of JPA [JobsRepository]
 * @property loadAllDataService Autowired instance of service that loads all relevant data from the github jobs API
 */
@Controller
class JobsController {
    @Autowired
    lateinit var jobsRepository: JobsRepository

    @Autowired
    lateinit var loadAllDataService: LoadAllDataService

    /**
     * REST GET function that displays a webpage with all of the jobs data.
     * If Jobs table is empty, it will be reloaded from the github jobs API.
     *
     * @param model A link to the UI model for loading attributes to populate mustache template variables
     * @return name of mustache template to load
     */
    @GetMapping("/jobs")
    fun jobs(model: Model): String {
        var allJobs = jobsRepository.findAllByOrderByLocationAscLanguageAsc()
        //if jobs table is empty reload it
        if(allJobs.count() == 0){
            loadAllDataService.loadAll()
            allJobs = jobsRepository.findAllByOrderByLocationAscLanguageAsc()
        }
        var denominator = -1
        for (job in allJobs){
            //take the first instance for each location, which should have a null language, and use that record as the denominator for calculating percentages
            if (job.language == null){
                denominator = job.count
                //Leave the percentage as 0 if there are no jobs in that location to avoid confusion, otherwise this record is all so set to 100
                if (job.count > 0) {
                    job.percentageByLocation = 100
                }
            } else if (denominator > 0) {
                //add the calcuated percentage to the JPA object's transitive variable
                job.percentageByLocation = ((job.count.toDouble() / denominator) * 100).roundToInt()
            }
        }
        //add the jpa object to the model
        model.addAttribute("jobsItem", allJobs)
        return "jobs"
    }
}
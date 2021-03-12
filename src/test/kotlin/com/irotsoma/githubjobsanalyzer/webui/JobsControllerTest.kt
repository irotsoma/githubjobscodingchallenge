package com.irotsoma.githubjobsanalyzer.webui

import com.irotsoma.githubjobsanalyzer.data.Jobs
import com.irotsoma.githubjobsanalyzer.data.JobsRepository
import com.irotsoma.githubjobsanalyzer.data.Location
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * Simple tests to make sure controllers load.
 *
 * @property jobsRepository Mocks the jobs repository so tests can return mocked data from the database
 * @property mockMvc A mock for the MVC to test the web controllers
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
internal class ControllerTests {

    @MockBean
    lateinit var jobsRepository: JobsRepository

    @Autowired
    lateinit var mockMvc: MockMvc

    /**
     * Test for /jobs
     * Mocks a single job entry to prevent loading from the GitHub Jobs API
     */
    @Test
    fun jobs() {
        Mockito.`when`(jobsRepository.findAllByOrderByLocationAscLanguageAsc()).thenReturn(listOf(Jobs(null, Location("test"),1)))
        mockMvc.perform(get("/jobs")).andExpect(status().isOk)
    }

    /**
     * Test for /
     */
    @Test
    fun home() {
        mockMvc.perform(get("/")).andExpect(status().isOk)
    }
}
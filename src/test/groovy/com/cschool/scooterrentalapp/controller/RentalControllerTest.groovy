package com.cschool.scooterrentalapp.controller

import com.cschool.scooterrentalapp.common.MsgSource
import com.cschool.scooterrentalapp.domain.model.Rental
import com.cschool.scooterrentalapp.domain.repository.DockStationRepository
import com.cschool.scooterrentalapp.domain.repository.RentalRepository
import com.cschool.scooterrentalapp.domain.repository.ScooterRepository
import com.cschool.scooterrentalapp.domain.repository.UserRepository
import com.cschool.scooterrentalapp.service.RentalServiceImpl
import com.cschool.scooterrentalapp.service.interfaces.RentalService
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import spock.lang.Specification
import spock.mock.DetachedMockFactory
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
class RentalControllerTest extends Specification {

    @Autowired
    private MockMvc mvc
    @Autowired
    MsgSource msgSource
    private RentalService rentalService
    private RentalController rentalController
    private RentalRepository rentalRepository
    private DockStationRepository dockStationRepository
    private UserRepository userRepository
    private ScooterRepository scooterRepository

    def setup() {
        rentalRepository = Mock(RentalRepository)
        dockStationRepository = Mock(DockStationRepository)
        userRepository = Mock(UserRepository)
        scooterRepository = Mock(ScooterRepository)

        rentalService = new RentalServiceImpl(msgSource, userRepository, scooterRepository, dockStationRepository, rentalRepository)
        rentalController = new RentalController(rentalService)

        mvc = standaloneSetup(rentalController).build()
    }

    def "when get all rental should return empty list"() {
        given:
        def rental = Rental.builder().id(1).build()
        1 * rentalRepository.findAll() >> [rental]
        when:

        def result = mvc.perform(get("/rental/getAllRentals"))

        then:
        result.andExpect(status().isOk())

        and:
        result.andReturn().getResponse().getContentAsString()
    }

}

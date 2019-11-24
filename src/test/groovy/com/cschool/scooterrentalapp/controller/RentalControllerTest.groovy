package com.cschool.scooterrentalapp.controller

import com.cschool.scooterrentalapp.common.MsgSource
import com.cschool.scooterrentalapp.domain.model.Rental
import com.cschool.scooterrentalapp.domain.repository.DockStationRepository
import com.cschool.scooterrentalapp.domain.repository.RentalRepository
import com.cschool.scooterrentalapp.domain.repository.ScooterRepository
import com.cschool.scooterrentalapp.domain.repository.UserRepository
import com.cschool.scooterrentalapp.service.RentalServiceImpl
import com.cschool.scooterrentalapp.service.interfaces.RentalService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ContextConfiguration(classes = MsgSource.class)
class RentalControllerTest extends Specification {

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

    def "when get all rental should return list with one rental"() {
        given:
        def rental = Rental.builder().id(1).build()
        1 * rentalRepository.findAll() >> [rental]

        when:
        def result = mvc.perform(get("/rental/getAllRentals"))

        then:
        result.andExpect(status().isOk())

        and:
        result.andReturn().getResponse().getContentAsString() == asJsonString([rental])
    }

    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper()
            final String jsonContent = mapper.writeValueAsString(obj)
            return jsonContent
        } catch (Exception e) {
            throw new RuntimeException(e)
        }
    }
}

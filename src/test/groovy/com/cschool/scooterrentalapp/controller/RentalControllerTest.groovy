package com.cschool.scooterrentalapp.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.RequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
@SpringBootTest
@AutoConfigureMockMvc
@WebMvcTest
class RentalControllerTest extends Specification{

    @Autowired
    private MockMvc mvc
    def setup(){
        mvc = MockMvcBuilders.standaloneSetup(new RentalController()).build()
    }
    def "when get all rental should return empty list" () {
        expect:

        mvc.perform(get("/rental/getAllRentals"))
        .andExpect(status().isOk())

    }
}

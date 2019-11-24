package com.cschool.scooterrentalapp.controller

import com.cschool.scooterrentalapp.ScooterRentalAppApplication
import com.cschool.scooterrentalapp.api.request.CreateUserAccountRequest
import com.cschool.scooterrentalapp.common.MsgSource
import com.cschool.scooterrentalapp.domain.model.UserAccount
import com.cschool.scooterrentalapp.domain.repository.UserRepository
import com.cschool.scooterrentalapp.service.UserAccountServiceImpl
import com.cschool.scooterrentalapp.service.interfaces.UserAccountService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

@SpringBootTest(classes = ScooterRentalAppApplication)
class UserAccountControllerTest extends Specification {

    private MockMvc mvc
    @Autowired
    private MsgSource msgSource
    private UserRepository userRepository
    private UserAccountController userAccountController
    private UserAccountService userAccountService

    def setup() {
        userRepository = Mock(UserRepository)
        userAccountService = new UserAccountServiceImpl(msgSource, userRepository)
        userAccountController = new UserAccountController(userAccountService)
        mvc = standaloneSetup(new UserAccountController(userAccountService)).build()
    }

    def "When proper user Request is passed, should return 'Poprawnie utworzono użytkownika'"() {
        given:
        def responseBody = "{" +
                "\"responseMsg\":\"Poprawnie utworzono konto użytkownika.\"," +
                "\"status\":\"SUCCESS\"," +
                "\"accountId\":16" +
                "}"

        def userRequest = new CreateUserAccountRequest()
        userRequest.setOwnerAge(5)
        userRequest.setOwnerEmail("string@string.pl")
        userRequest.setOwnerUserName("username")

        def newAccount = new UserAccount()
        newAccount.setId(16L)

        when:
        def result = mvc.perform(post("/account-user/create").content(asJsonString(userRequest)).contentType(MediaType.APPLICATION_JSON))

        then:
        1 * userRepository.save(_ as UserAccount) >> newAccount
        1 * userRepository.findByUserEmail(userRequest.getOwnerEmail()) >> []
        and:
        result.andExpect(status().isOk())
        result.andReturn().getResponse().getContentAsString() == responseBody

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
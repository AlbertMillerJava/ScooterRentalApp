package com.cschool.scooterrentalapp.controller;

import com.cschool.scooterrentalapp.api.request.CreateUserAccountRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RentalControllerJunitTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void ifGetAllRentalsShouldReturnEmptyList() throws Exception{
        mockMvc.perform(get("/rental/getAllRentals"))
                .andExpect(status().isOk());
    }

    @Test
    public void ifProperUserCreateAccountRequestThenProperMessage() throws Exception {

        String responseBody = "{\n" +
                "  \"responseMsg\": \"Poprawnie utworzono konto użytkownika.\",\n" +
                "  \"status\": \"SUCCESS\",\n" +
                "  \"accountId\": 16\n" +
                "}";

        CreateUserAccountRequest userRequest = new CreateUserAccountRequest();
        userRequest.setOwnerAge(5);
        userRequest.setOwnerEmail("string@string.pl");
        userRequest.setOwnerUserName("username");

        mockMvc.perform(post("/account-user/create").content(asJsonString(userRequest)).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(responseBody));
    }

    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

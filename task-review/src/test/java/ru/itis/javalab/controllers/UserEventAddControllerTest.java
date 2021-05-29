package ru.itis.javalab.controllers;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DisplayName(("UserAddController is working when"))
public class UserEventAddControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void add_event_test() throws Exception {
        mockMvc.perform(post("/addEvent")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                    "\"login\":\"test@gmail.com\",\n" +
                                    "\"logins\":null,\n" +
                                    "\"name\":\"пара JavaLab\",\n" +
                                    "\"eventStarts\":\"2029-12-25 00:00:00\",\n" +
                                    "\"eventEnds\":\"2029-12-25 01:30:00\"\n" +
                                    "}")
                            .characterEncoding("utf-8"))
                            .andExpect(status().isOk())
                            .andReturn();

    }


}

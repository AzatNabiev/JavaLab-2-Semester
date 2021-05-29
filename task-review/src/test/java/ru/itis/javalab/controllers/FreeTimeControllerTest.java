package ru.itis.javalab.controllers;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
public class FreeTimeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Test
    public void find_free_time() throws Exception {
        mockMvc.perform(post("/showFreeTime")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"logins\":[\"test1@gmail.com\",\"test@gmail.com\"]\n" +
                        "}")
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();
    }


}

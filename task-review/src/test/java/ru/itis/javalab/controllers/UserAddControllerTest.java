package ru.itis.javalab.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.services.UserService;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DisplayName(("UserAddController is working when"))
public class UserAddControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setUp(){
        UserDto userDto = UserDto.builder()
                .login("test@gmail.com")
                .name("Azat")
                .build();
        when(userService.addUser(userDto)).thenReturn(UserDto.builder()
        .login("test@gmail.com")
        .name("Azat")
        .build());



    }

    @Test
    public void add_user_test() throws Exception {
        String jsonVal = "{\n" +
                "  \"login\": \"test@gmail.com\",\n" +
                "  \"name\": \"Azat\"\n" +
                "}";
        mockMvc.perform(post("/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonVal)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Azat")))
                .andExpect(jsonPath("$.login", is("test@gmail.com")))
                .andReturn();

    }
    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}

package ru.itis.javalab.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.exception.IncorrectGivenData;
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
        UserDto testUser = UserDto.builder()
                .login("test@gmail.com")
                .name("Azat")
                .build();
        when(userService.addUser(testUser)).thenReturn(UserDto.builder()
        .login("test@gmail.com")
        .name("Azat")
        .build());

        doThrow(new IncorrectGivenData("Incorrect given data"))
                .when(userService)
                .addUser(UserDto.builder()
                .login("testtest").build());
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
    @Test
    public void throws_exception_for_incorrect_email() throws Exception {
        mockMvc.perform(post("/userAdd")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"login\": \"testgmail.com\",\n" +
                        "  \"name\": \"Daniil\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

}

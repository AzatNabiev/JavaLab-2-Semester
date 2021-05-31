package ru.itis.javalab.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.javalab.dto.EventDto;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.exception.IncorrectGivenData;
import ru.itis.javalab.services.UsersEventAddService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DisplayName(("UserAddController is working when"))
public class UsersEventAddControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersEventAddService usersEventAddService;

    @BeforeEach
    public void setUp(){
        LocalDate eventDate = LocalDate.of(29,12,25);
        LocalTime eventTime = LocalTime.of(1,20,31);
        LocalDateTime eventStarts = LocalDateTime.of(eventDate,eventTime);
        eventDate = LocalDate.of(29,12,25);
        eventTime = LocalTime.of(1,30,31);
        LocalDateTime eventEnds = LocalDateTime.of(eventDate,eventTime);

        EventDto eventDto = EventDto.builder()
                .logins(Arrays.asList("test@gmail.com","test1@gmail.com"))
                .eventStarts(eventStarts)
                .eventEnds(eventEnds)
                .build();
        when(usersEventAddService.addEvents(eventDto)).thenReturn(
                EventDto.builder()
                        .logins(Arrays.asList("test@gmail.com","test1@gmail.com"))
                        .eventStarts(eventStarts)
                        .eventEnds(eventEnds)
                        .build());
        doThrow(new IncorrectGivenData("Incorrect given data"))
                .when(usersEventAddService)
                .addEvents(EventDto.builder()
                        .logins(Arrays.asList("testgmail.com","test1@gmail.com")).build());
    }

    @Test
    public void add_event_for_users() throws Exception {
        mockMvc.perform(post("/addSeveralEvents")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "   \"logins\":[\"test1@gmail.com\",\"test@gmail.com\"],\n" +
                        "   \"name\": \"Созвон по проекту\",\n" +
                        "   \"eventStarts\": \"2019-12-25 19:00:13\",\n" +
                        "   \"eventEnds\": \"2019-12-25 20:30:00\"\n" +
                        "}")
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    public void throws_exception_for_incorrect_emails() throws Exception {
        mockMvc.perform(post("/addSeveralEvents")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "   \"logins\":[\"tesgmail.com\",\"test@gmail.com\"]\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}

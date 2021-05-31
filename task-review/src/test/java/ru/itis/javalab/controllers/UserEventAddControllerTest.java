package ru.itis.javalab.controllers;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.itis.javalab.dto.EventDto;
import ru.itis.javalab.services.UserEventService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DisplayName(("UserAddController is working when"))
public class UserEventAddControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserEventService userEventService;

    @BeforeEach
    public void setUp(){
        LocalDate eventDate = LocalDate.of(29,12,25);
        LocalTime eventTime = LocalTime.of(1,20,31);
        LocalDateTime eventStarts = LocalDateTime.of(eventDate,eventTime);
        eventDate = LocalDate.of(29,12,25);
        eventTime = LocalTime.of(1,30,31);
        LocalDateTime eventEnds = LocalDateTime.of(eventDate,eventTime);

        EventDto eventDto = EventDto.builder()
                .login("test@gmail.com")
                .eventStarts(eventStarts)
                .eventEnds(eventEnds)
                .build();
        when(userEventService.addEvent(eventDto)).thenReturn(EventDto.builder()
                .login("test@gmail.com")
                .name("JavaLab")
                .eventStarts(eventStarts)
                .eventEnds(eventEnds)
                .build());
    }

    @Test
    public void add_event_test() throws Exception {
        String jsonVal = "{\n" +
                "\"login\":\"test@gmail.com\",\n" +
                "\"logins\":null,\n" +
                "\"name\":\"JavaLab\",\n" +
                "\"eventStarts\":\"2029-12-25 01:20:31\",\n" +
                "\"eventEnds\":\"2029-12-25 01:30:31\"\n" +
                "}";
        mockMvc.perform(post("/addEvent")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(jsonVal)
                            .characterEncoding("utf-8"))
                            .andDo(print())
                            .andExpect(status().isOk())
                            .andReturn();
    }


}

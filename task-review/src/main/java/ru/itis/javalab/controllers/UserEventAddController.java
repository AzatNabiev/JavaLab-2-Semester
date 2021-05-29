package ru.itis.javalab.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.dto.EventDto;
import ru.itis.javalab.services.UserEventService;

@RestController
public class UserEventAddController {

    private UserEventService userEventService;

    private UserEventAddController(UserEventService userEventService){
        this.userEventService = userEventService;
    }

    @PostMapping("/addEvent")
    public ResponseEntity<EventDto> addEvent(@RequestBody EventDto eventDto){
        return ResponseEntity.ok(userEventService.addEvent(eventDto));
    }
}

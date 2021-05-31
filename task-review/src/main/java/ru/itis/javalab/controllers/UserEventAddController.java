package ru.itis.javalab.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.dto.EventDto;
import ru.itis.javalab.services.UserEventService;
import ru.itis.javalab.services.ValidationService;

@RestController
public class UserEventAddController {

    private UserEventService userEventService;
    private ValidationService validationService;

    @Autowired
    public UserEventAddController(UserEventService userEventService, ValidationService validationService){
        this.validationService =validationService;
        this.userEventService = userEventService;
    }

    @PostMapping("/addEvent")
    public ResponseEntity<EventDto> addEvent(@RequestBody EventDto eventDto){
        validationService.validateEventDto(eventDto);
        return ResponseEntity.ok(userEventService.addEvent(eventDto));
    }
}

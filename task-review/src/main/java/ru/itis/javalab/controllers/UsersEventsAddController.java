package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.dto.EventDto;
import ru.itis.javalab.services.UsersEventAddService;
import ru.itis.javalab.services.ValidationService;

@RestController
public class UsersEventsAddController {

    private UsersEventAddService usersEventAddService;
    private ValidationService validationService;

    @Autowired
    public UsersEventsAddController(ValidationService validationService,UsersEventAddService usersEventAddService){
        this.validationService = validationService;
        this.usersEventAddService = usersEventAddService;
    }

    @PostMapping("/addSeveralEvents")
    public ResponseEntity<EventDto> addEvents(@RequestBody EventDto eventDto){
        validationService.validateEventDto(eventDto);
        return ResponseEntity.ok(usersEventAddService.addEvents(eventDto));
    }
}

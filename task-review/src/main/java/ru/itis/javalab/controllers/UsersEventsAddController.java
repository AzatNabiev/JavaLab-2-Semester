package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.dto.EventDto;
import ru.itis.javalab.services.UsersEventService;

@RestController
public class UsersEventsAddController {

    private UsersEventService usersEventService;

    @Autowired
    public UsersEventsAddController(UsersEventService usersEventService){
        this.usersEventService = usersEventService;
    }

    @PostMapping("/addSeveralEvents")
    public ResponseEntity<EventDto> addEvents(@RequestBody EventDto eventDto){

        return ResponseEntity.ok(usersEventService.addEvents(eventDto));
    }
}

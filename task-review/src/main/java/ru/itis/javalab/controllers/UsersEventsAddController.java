package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.dto.EventDto;
import ru.itis.javalab.services.UsersEventAddService;

@RestController
public class UsersEventsAddController {

    private UsersEventAddService usersEventAddService;

    @Autowired
    public UsersEventsAddController(UsersEventAddService usersEventAddService){
        this.usersEventAddService = usersEventAddService;
    }

    @PostMapping("/addSeveralEvents")
    public ResponseEntity<EventDto> addEvents(@RequestBody EventDto eventDto){

        return ResponseEntity.ok(usersEventAddService.addEvents(eventDto));
    }
}

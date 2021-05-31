package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.dto.EventDto;
import ru.itis.javalab.models.Event;
import ru.itis.javalab.services.ScheduleService;
import ru.itis.javalab.services.ValidationService;

import java.util.List;


@RestController
public class FreeTimeController {

    private ScheduleService scheduleService;
    private ValidationService validationService;

    @Autowired
    public FreeTimeController(ValidationService validationService,ScheduleService scheduleService){
        this.validationService = validationService;
        this.scheduleService = scheduleService;
    }

    @PostMapping("/showFreeTime")
    public ResponseEntity<List<EventDto>> getFreeTime(@RequestBody EventDto eventDto){
        validationService.validateEventDto(eventDto);
        List<EventDto> events = scheduleService.getFreeTime(eventDto);
        return ResponseEntity.ok(events);
    }
}

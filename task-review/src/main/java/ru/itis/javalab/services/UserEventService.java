package ru.itis.javalab.services;

import ru.itis.javalab.dto.EventDto;
import ru.itis.javalab.models.User;

import java.time.LocalDateTime;

public interface UserEventService {
    EventDto addEvent(EventDto eventDto);
    Boolean isEventExist(LocalDateTime eventStarts, LocalDateTime eventEnds, User user);
}

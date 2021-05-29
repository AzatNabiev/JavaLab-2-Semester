package ru.itis.javalab.services;

import ru.itis.javalab.dto.EventDto;
import ru.itis.javalab.models.User;

import java.util.List;

public interface UsersEventAddService {
    EventDto addEvents(EventDto eventDto);
    List<User> checkExistingUsers(String[] userLogins);
}

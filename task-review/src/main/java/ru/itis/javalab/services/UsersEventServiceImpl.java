package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.javalab.dto.EventDto;
import ru.itis.javalab.exception.NoSuchEventException;
import ru.itis.javalab.exception.NoSuchUserException;
import ru.itis.javalab.models.Event;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.EventRepository;
import ru.itis.javalab.repositories.UserRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersEventServiceImpl implements UsersEventService {

    private UserRepository userRepository;
    private EventRepository eventRepository;
    private UserEventService userEventService;

    @Autowired
    public UsersEventServiceImpl(UserRepository userRepository, EventRepository eventRepository,
                                 UserEventService userEventService) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.userEventService = userEventService;
    }

    @Override
    @Transactional
    public EventDto addEvents(EventDto eventDto){
        List<User> users = checkExistingUsers(eventDto.getLogins());
        if (!users.isEmpty()) {
            LocalDateTime currentTime = LocalDateTime.now();
            for (User user : users) {
                if (!userEventService.isEventExist(eventDto.getEventStarts(), eventDto.getEventEnds(), user.getId())) {
                    throw new NoSuchEventException("already has event");
                } else {
                    Event newEvent = Event.builder().eventName(eventDto.getName())
                            .addedTime(currentTime)
                            .eventStarts(eventDto.getEventStarts())
                            .eventEnds(eventDto.getEventEnds())
                            .user(user)
                            .build();
                    eventRepository.save(newEvent);
                }
            }
        } else {
            throw new NoSuchUserException("no such user");
        }
        return eventDto;
    }
    @Override
    public List<User> checkExistingUsers(String[] userLogins) {
        List<User> users = new ArrayList<>();
        for (String userLogin : userLogins) {
            Optional<User> user = userRepository.findByEmail(userLogin);
            if (user.isPresent()) {
                users.add(user.get());
            } else {
                users.clear();
                break;
            }
        }
        return users;
    }

}

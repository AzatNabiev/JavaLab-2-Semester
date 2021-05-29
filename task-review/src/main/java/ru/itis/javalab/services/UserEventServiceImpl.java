package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.EventDto;
import ru.itis.javalab.exception.NoSuchEventException;
import ru.itis.javalab.exception.NoSuchUserException;
import ru.itis.javalab.models.Event;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.EventRepository;
import ru.itis.javalab.repositories.UserRepository;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserEventServiceImpl implements UserEventService {

    private EventRepository eventRepository;
    private UserRepository userRepository;

    @Autowired
    public UserEventServiceImpl(EventRepository eventRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public EventDto addEvent(EventDto eventDto) {

        Optional<User> userOptional = userRepository.findByEmail(eventDto.getLogin());

        if (userOptional.isPresent()) {
            User currentUser = userOptional.get();
            LocalDateTime currentTime = LocalDateTime.now();
            if (!isEventExist(eventDto.getEventStarts(), eventDto.getEventEnds(), currentUser.getId())) {
                Event newEvent = Event.builder().eventName(eventDto.getName())
                        .addedTime(currentTime)
                        .eventStarts(eventDto.getEventStarts())
                        .eventEnds(eventDto.getEventEnds())
                        .user(currentUser)
                        .build();
                eventRepository.save(newEvent);
            } else {
                throw new NoSuchEventException("no such event");
            }
        } else {
            throw new NoSuchUserException("no such user");
        }
        return eventDto;
    }

    @Override
    public Boolean isEventExist(LocalDateTime eventStarts, LocalDateTime eventEnds, Long userId) {
        return eventRepository.checkExistingEvent(eventStarts, eventEnds, userId);
    }

}

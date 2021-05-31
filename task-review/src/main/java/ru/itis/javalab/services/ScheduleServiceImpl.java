package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.EventDto;
import ru.itis.javalab.exception.NoFreeTimeException;
import ru.itis.javalab.exception.NoSuchEventException;
import ru.itis.javalab.models.Event;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.EventRepository;
import ru.itis.javalab.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private EventRepository eventRepository;
    private UserRepository userRepository;

    @Autowired
    public ScheduleServiceImpl(EventRepository eventRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public List<EventDto> getFreeTime(EventDto eventDto) {
        List<User> users = checkExistingUsers(eventDto.getLogins());
        List<Event> usersFreeTime;
        List<Event> mergedFreeTime = new ArrayList<>();

        if (!users.isEmpty()) {
            for (User user : users) {
                usersFreeTime = eventRepository.getFreeTime(user.getId());
                if (!usersFreeTime.isEmpty()) {
                    if (mergedFreeTime.isEmpty()) {
                        mergedFreeTime.addAll(usersFreeTime);
                    } else {
                        mergedFreeTime = getMergedCommonSchedule(mergedFreeTime, usersFreeTime);
                    }
                } else {
                    mergedFreeTime = null;
                    break;
                }
            }
        } else {
            throw new NoSuchEventException("no such user");
        }
        if (mergedFreeTime == null){
            throw new NoFreeTimeException("no free time");
        } else {
            return EventDto.from(mergedFreeTime);
        }
    }

    @Override
    public List<User> checkExistingUsers(List<String> userLogins) {
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

    @Override
    public Optional<Event> findIntersection(List<Event> mergedFreeTime, Event event) {
        Event newEvent=null;
        LocalDateTime eventStarts = event.getEventStarts();
        LocalDateTime eventEnds = event.getEventEnds();
        for (int i=0; i<mergedFreeTime.size();i++){
            LocalDateTime freeTimeStarts = mergedFreeTime.get(i).getEventStarts();
            LocalDateTime freeTimeEnds = mergedFreeTime.get(i).getEventEnds();
            if (((eventStarts.isBefore(freeTimeStarts) || (eventStarts.isEqual(freeTimeStarts))) && eventStarts.isBefore(freeTimeEnds))
                    && (eventEnds.isAfter(freeTimeStarts) && (eventEnds.isBefore(freeTimeEnds) || eventEnds.isEqual(freeTimeEnds)))){
                newEvent = Event.builder()
                        .eventStarts(freeTimeStarts)
                        .eventEnds(eventEnds)
                        .build();
                break;
            } else if ( ((eventStarts.isAfter(freeTimeStarts) || eventStarts.isEqual(freeTimeStarts))
                    &&(eventStarts.isBefore(freeTimeEnds))) && (eventEnds.isAfter(freeTimeEnds))){
                newEvent = Event.builder()
                        .eventStarts(eventStarts)
                        .eventEnds(freeTimeEnds)
                        .build();
                break;
            } else if( eventStarts.isAfter(freeTimeStarts) && eventEnds.isBefore(freeTimeEnds)) {
                newEvent = Event.builder()
                        .eventStarts(eventStarts)
                        .eventEnds(eventEnds)
                        .build();
                break;
            } else if(eventStarts.isBefore(freeTimeStarts) && eventEnds.isAfter(freeTimeEnds)){
                newEvent = Event.builder()
                        .eventStarts(freeTimeStarts)
                        .eventEnds(freeTimeEnds)
                        .build();
                break;
            }
        }
        return Optional.ofNullable(newEvent);
    }

    @Override
    public List<Event> getMergedCommonSchedule(List<Event> mergedFreeTime, List<Event> usersFreeTime) {
        List<Event> newMergedFreeTime = new ArrayList<>();
        Optional<Event> intersectedEvent;
        for (Event event : usersFreeTime) {
            intersectedEvent = findIntersection(mergedFreeTime, event);
            intersectedEvent.ifPresent(newMergedFreeTime::add);
        }
        return newMergedFreeTime;
    }
}

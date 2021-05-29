package ru.itis.javalab.services;

import ru.itis.javalab.dto.EventDto;
import ru.itis.javalab.models.Event;
import ru.itis.javalab.models.User;
import java.util.List;
import java.util.Optional;

public interface ScheduleService {
    List<EventDto> getFreeTime(EventDto eventDto);
    List<User> checkExistingUsers(String[] userLogins);
    Optional<Event> findIntersection(List<Event> mergedFreeTime, Event event);
    List<Event> getMergedCommonSchedule(List<Event> mergedFreeTime, List<Event> usersFreeTime);
}

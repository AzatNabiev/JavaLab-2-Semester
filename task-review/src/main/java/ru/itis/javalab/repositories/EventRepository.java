package ru.itis.javalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.models.Event;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
        @Query(value = "select exists(select * from event ev where not (:eventStarts <= ev.event_ends and :eventEnds>= ev.event_starts ) and user_id=:userId)", nativeQuery = true)
        boolean checkExistingEvent(@Param("eventStarts") LocalDateTime eventStarts,
                                   @Param("eventEnds") LocalDateTime eventEnds,
                                   @Param("userId") Long userId);

        @Query(value="SELECT id,added_time, event_name,user_id, prev_end_time as event_starts, event_starts as event_ends FROM (SELECT *, lag(event_ends) OVER (ORDER BY event_starts) as prev_end_time FROM event where user_id = :userId ORDER BY event_starts) s WHERE event_starts > prev_end_time and user_id = :userId order by event_starts",
        nativeQuery=true)
        List<Event> getFreeTime(@Param("userId") Long userId);


}

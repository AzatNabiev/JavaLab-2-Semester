package ru.itis.javalab.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import ru.itis.javalab.models.Event;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventDto {
    @Email
    private String login;
    private List<@Email String> logins;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventStarts;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventEnds;

    public static EventDto from(Event event){
        return EventDto.builder()
                .eventStarts(event.getEventStarts())
                .eventEnds(event.getEventEnds())
                .build();
    }

    public static List<EventDto> from(List<Event> events){
        return events.stream().map(EventDto::from).collect(Collectors.toList());
    }
}

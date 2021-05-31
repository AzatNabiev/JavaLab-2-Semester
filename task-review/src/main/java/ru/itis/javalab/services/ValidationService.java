package ru.itis.javalab.services;

import ru.itis.javalab.dto.EventDto;
import ru.itis.javalab.dto.UserDto;

public interface ValidationService {
    void validateUserDto(UserDto userDto);
    void validateEventDto(EventDto eventDto);
}

package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.EventDto;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.exception.IncorrectGivenData;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class ValidationServiceImpl implements ValidationService {

    private final Validator validator;

    @Autowired
    public ValidationServiceImpl(Validator validator){
        this.validator = validator;
    }

    @Override
    public void validateUserDto(UserDto userDto) {
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(userDto);
        if (!constraintViolations.isEmpty()) {
            throw new IncorrectGivenData("Incorrect data was given");
        }
    }

    @Override
    public void validateEventDto(EventDto eventDto) {
        Set<ConstraintViolation<EventDto>> constraintViolations = validator.validate(eventDto);
        if (!constraintViolations.isEmpty()) {
            throw new IncorrectGivenData("Incorrect data was given");
        }
    }
}

package ru.itis.javalab.advice;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.itis.javalab.exception.NoFreeTimeException;
import ru.itis.javalab.exception.NoSuchEventException;
import ru.itis.javalab.exception.NoSuchUserException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NoSuchUserException.class)
    public ResponseEntity<Object> handleNoSuchUserException(
            NoSuchUserException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "No such user found");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NoSuchEventException.class)
    public ResponseEntity<Object> handleNoSuchEventException(
            NoSuchEventException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "No such event found");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleNoSuchEventException(
            ConstraintViolationException ex, WebRequest webRequest){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "User already exist");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoFreeTimeException.class)
    public ResponseEntity<Object> handleNoFreeTimeException(
            NoFreeTimeException ex, WebRequest webRequest){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "There is no free time");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}

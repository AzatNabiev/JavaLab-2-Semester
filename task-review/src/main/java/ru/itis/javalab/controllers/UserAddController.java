package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.services.UserService;
import ru.itis.javalab.services.ValidationService;

@RestController
public class UserAddController {

    private ValidationService validationService;
    private UserService userService;

    @Autowired
    public UserAddController(ValidationService validationService, UserService userService) {
        this.validationService = validationService;
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        validationService.validateUserDto(userDto);
        return ResponseEntity.ok(userService.addUser(userDto));
    }
}

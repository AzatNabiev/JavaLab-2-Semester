package ru.itis.javalab.web.services;

import ru.itis.javalab.web.dto.UserDto;

import java.util.List;

public interface UsersService {
    List<UserDto> getAllUsers();
    void banAll();

}

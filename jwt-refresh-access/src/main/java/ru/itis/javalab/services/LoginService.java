package ru.itis.javalab.services;

import ru.itis.javalab.dto.EmailPasswordDto;
import ru.itis.javalab.dto.TokenDto;
import ru.itis.javalab.models.User;

import java.util.Map;

public interface LoginService {
    Map<String,TokenDto> login(EmailPasswordDto emailPassword);
    TokenDto getAccessToken(User user);
    TokenDto getRefreshToken(User user);
}

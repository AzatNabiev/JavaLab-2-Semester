package ru.itis.javalab.services;

import ru.itis.javalab.dto.TokenDto;

public interface RefreshService {
    boolean validateToken(String token);
    TokenDto refreshed(String token);

}

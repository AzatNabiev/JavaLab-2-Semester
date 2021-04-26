package ru.itis.javalab.services;

import ru.itis.javalab.models.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id);
    User findByEmail(String email);
}

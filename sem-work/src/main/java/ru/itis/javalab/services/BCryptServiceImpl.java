package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BCryptServiceImpl implements BCryptService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean checkPass(String authPass, String dbPass) {
        return passwordEncoder.matches(authPass, dbPass);
    }

    @Override
    public String cryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
}

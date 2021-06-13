package ru.itis.javalab.services;

public interface BCryptService {
    boolean checkPass(String authPass, String dbPass);
    String cryptPassword(String password);
}

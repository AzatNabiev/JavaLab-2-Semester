package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.dto.EmailPasswordDto;
import ru.itis.javalab.dto.TokenDto;
import ru.itis.javalab.security.token.JwtAuthResponse;
import ru.itis.javalab.services.LoginService;

import java.util.Map;

@RestController
public class LoginController {

    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody EmailPasswordDto emailPassword){
            Map<String, TokenDto> tokens = loginService.login(emailPassword);
            return ResponseEntity.ok(new JwtAuthResponse(tokens.get("accessToken").getToken(),tokens.get("refreshToken").getToken()));
    }
}

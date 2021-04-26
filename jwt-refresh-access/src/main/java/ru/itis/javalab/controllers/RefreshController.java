package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.services.RefreshService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class RefreshController {

    @Autowired
    private RefreshService refreshService;

    @GetMapping("/refresh")
    public ResponseEntity<?> refreshToken(HttpServletRequest request,HttpServletResponse response){
        String token = request.getHeader("Authorization");
        if (token != null && refreshService.validateToken(token)){
            return ResponseEntity.ok(refreshService.refreshed(token));
        } else {
            return ResponseEntity.ok("Incorrect token");
        }
    }
}

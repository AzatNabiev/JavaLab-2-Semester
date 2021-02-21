package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.javalab.dto.UserSignUpForm;
import ru.itis.javalab.services.SignUpService;

@Controller
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @GetMapping("/signUp")
    public String signUp(){
        return "sign_up";
    }

    @PostMapping("/signUpp")
    public String signUpp(UserSignUpForm user){
        signUpService.signUp(user);
        return null;
    }
}

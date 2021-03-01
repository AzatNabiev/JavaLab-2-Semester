package ru.itis.javalab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.javalab.dto.UserSignInForm;

@Controller
public class SignInController {
    @GetMapping("/signIn")
    public String signIn(){
        return "sign_in";
    }

    @PostMapping("/signInn")
    public String signInn(UserSignInForm user){
        System.out.println(user.toString());
        return null;
    }
}

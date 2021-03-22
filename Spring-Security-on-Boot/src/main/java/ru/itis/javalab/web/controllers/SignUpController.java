package ru.itis.javalab.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.javalab.web.dto.UserForm;
import ru.itis.javalab.web.services.SignUpService;

import javax.annotation.security.PermitAll;

@Controller
public class SignUpController {
    @Autowired
    private SignUpService signUpService;

//    @PermitAll
    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "sign_up_page";
    }

//    @PermitAll
    @PostMapping("/signUp")
    public String signUp(UserForm form) {
        signUpService.signUp(form);
        return "redirect:/signIn";
    }

}

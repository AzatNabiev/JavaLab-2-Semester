package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.javalab.dto.UserSignInForm;
import ru.itis.javalab.services.SignInService;

import javax.validation.Valid;

@Controller
public class SignInController {

    @Autowired
    private SignInService signInService;

    @GetMapping("/signInnnnnnnnnn")
    public String signIn(Model model){
        model.addAttribute("userSignInForm", new UserSignInForm());
        return "sign_in";
    }

    @PostMapping("/signInnnnnnnnnnnnnn")
    public String signIn(@Valid UserSignInForm form,BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("userSignInForm", form);
            return "sign_in";
        } else if (signInService.checkEmailAndPass(form)){
            return "redirect:/profile";
        } else {
            model.addAttribute("namesErrorMessage","Error in signing in.");
            return "sign_in";
        }
    }
}

package ru.itis.javalab.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.javalab.dto.UserSignUpForm;
import ru.itis.javalab.services.SignUpService;
import javax.validation.Valid;
import java.util.Objects;

@Controller
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @GetMapping("/signUp")
    public String signUp(Model model){
        model.addAttribute("userSignUpForm", new UserSignUpForm());
        return "sign_up";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid UserSignUpForm form, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().anyMatch(error ->{
                if (Objects.requireNonNull(error.getCodes())[0].equals("userSignUpForm.ValidNames")){
                    model.addAttribute("namesErrorMessage", error.getDefaultMessage());
                }
                return true;
            });
            model.addAttribute("userSignUpForm", form);
            return "sign_up";
            }
        signUpService.signUp(form);
        return "redirect:/signIn";
    }

    @GetMapping("/confirm/{confirm_code}")
    public String confirm(@PathVariable("confirm_code")String confirm){
        signUpService.confirmUser(confirm);
        return "redirect:/signIn";
    }



}

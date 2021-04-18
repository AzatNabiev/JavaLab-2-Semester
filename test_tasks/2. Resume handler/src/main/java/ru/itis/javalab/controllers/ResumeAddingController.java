package ru.itis.javalab.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.javalab.dto.ResumeDto;
import ru.itis.javalab.services.ResumeService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Set;

@Controller
public class ResumeAddingController {

    @Autowired
    private ResumeService resumeService;
    @Autowired
    private Validator validator;

    @GetMapping("/addResume")
    public String addResume(Model model){
        model.addAttribute("errors", new ArrayList<>());
        return "resume_page";
    }
    @PostMapping("/addResume")
    public String addResume(ResumeDto resumeDto, Model model){
        Set<ConstraintViolation<ResumeDto>> constraintViolations = validator.validate(resumeDto);
        if (!constraintViolations.isEmpty()){
            model.addAttribute("errors",constraintViolations);
        } else {
            resumeService.save(resumeDto);
        }
        return "resume_page";
    }
}

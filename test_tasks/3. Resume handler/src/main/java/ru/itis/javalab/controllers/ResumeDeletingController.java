package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.javalab.services.ResumeService;

@Controller
public class ResumeDeletingController {
    @Autowired
    private ResumeService resumeService;

    @PostMapping("/deleteResume/{resume-id}")
    public String deleteResume(@PathVariable("resume-id")String resumeId){
            resumeService.deleteResumeById(resumeId);
            return "redirect:/resumes";
    }
}

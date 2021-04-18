package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.javalab.dto.ResumeDto;
import ru.itis.javalab.services.ResumeService;

import javax.servlet.http.HttpSession;

@Controller
public class ResumeChangerController {
    @Autowired
    private ResumeService resumeService;

    @PostMapping("/changePost")
    public String changePost(ResumeDto resumeDto, HttpSession httpSession){
        String resumeId= (String) httpSession.getAttribute("resumeId");
        resumeDto.set_id(resumeId);
        resumeService.changeResumeById(resumeDto);
        return "redirect:/resumes";
    }
}

package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.javalab.dto.ResumeDto;
import ru.itis.javalab.models.Resume;
import ru.itis.javalab.services.ResumeService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ResumeGettingController {

    @Autowired
    private ResumeService resumeService;

    @GetMapping("/resumes")
    public String getResumePage(Model model){
        model.addAttribute("resumes", new ArrayList<>());
        return "resume_getting_page";
    }

    @GetMapping("/getAllResumes")
    public String getAllResumes(Model model){
        List<ResumeDto> resumes = resumeService.getAll();
        model.addAttribute("resumes", resumes);
        return "resume_getting_page";
    }

   @GetMapping("/getAllResumesById")
    public String getResumeById(@RequestParam("post-id") String postId, Model model, HttpSession httpSession) {
       Optional<Resume> resume = resumeService.getResumeDtoById(postId);

       resume.ifPresent(value -> {model.addAttribute("resumeById", value);
                                  httpSession.setAttribute("resumeId",value.get_id());});
       return "resume_getting_page";
   }
}

package ru.itis.javalab.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import ru.itis.javalab.services.VideoUploadService;

import java.io.IOException;

@Controller
public class ProfileController {

    @Autowired
    private VideoUploadService videoUploadService;

    @GetMapping("/profile")
    public String getProfile(Model model){
        return "profile";
    }

    @PostMapping("/uploadVideoWithData")
    public String saveVideoWithDescrip(@RequestParam("file") CommonsMultipartFile file, @RequestParam("name") String videoName){
        try {
            videoUploadService.saveFileToStorage(file.getInputStream(), videoName, file.getContentType(), file.getSize());
        } catch (IOException e){
            throw new IllegalArgumentException(e);
        }
        return null;
    }
}

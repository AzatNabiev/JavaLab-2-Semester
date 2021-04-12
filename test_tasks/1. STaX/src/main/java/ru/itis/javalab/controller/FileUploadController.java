package ru.itis.javalab.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.javalab.services.FileUploadService;

import javax.servlet.http.HttpSession;

@Controller
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @GetMapping("/uploadFile")
    public String getFile(HttpSession httpSession) {
        fileUploadService.uploadFile((Long) httpSession.getAttribute("id"));

        return null;
    }
}

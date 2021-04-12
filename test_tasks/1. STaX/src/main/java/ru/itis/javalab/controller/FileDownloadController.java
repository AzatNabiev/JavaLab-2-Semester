package ru.itis.javalab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.javalab.dto.FileInfoDto;
import ru.itis.javalab.services.FileDownloadService;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class FileDownloadController {

    @Autowired
    private FileDownloadService fileDownloadService;

    @PostMapping("/downloadFile")
    public String downloadFile(@RequestParam("file")MultipartFile file, HttpSession session){
        FileInfoDto fileInfoDto;
        try {
        fileInfoDto = fileDownloadService.saveFileToStorage(FileInfoDto.builder()
                                        .file(file.getInputStream())
                                        .contentType(file.getContentType())
                                        .size(file.getSize())
                                        .build());
        } catch (IOException e){
            throw new IllegalArgumentException(e);
        }
        session.setAttribute("id", fileInfoDto.getId());
        return "redirect:/uploadFile";
    }
}

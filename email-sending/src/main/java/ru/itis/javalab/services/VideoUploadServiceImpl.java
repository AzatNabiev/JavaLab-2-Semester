package ru.itis.javalab.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.models.VideoInfo;
import ru.itis.javalab.repositories.VideoInfoRepository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class VideoUploadServiceImpl implements VideoUploadService {

    @Autowired
    private VideoInfoRepository videoInfoRepository;

    @Override
    public void saveFileToStorage(InputStream file, String submittedFileName, String contentType, Long size) {
        VideoInfo videoInfo = VideoInfo.builder()
                .originalFileName(submittedFileName)
                .storageFileName(UUID.randomUUID().toString())
                .size(size)
                .type(contentType)
                .build();
        try {
            Files.copy(file, Paths.get("C://files/"+videoInfo.getStorageFileName()+"."+videoInfo.getType().split("/")[1]));
            //videoInfoRepository.save(videoInfo);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

    }
}

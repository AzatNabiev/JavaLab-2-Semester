package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.models.FileInfo;
import ru.itis.javalab.repositories.FileInfoJpaRepository;

import java.io.IOException;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private FileInfoJpaRepository fileInfoJpaRepository;

    @Autowired
    private Xml2JsonConverter xml2JsonConverter;

    @Override
    public void uploadFile(Long id) {
        FileInfo fileInfo = fileInfoJpaRepository.getOne(id);
        try {
            xml2JsonConverter.convertXmlToJson(fileInfo);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

    }


}

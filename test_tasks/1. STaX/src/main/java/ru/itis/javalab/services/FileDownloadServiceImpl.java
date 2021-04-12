package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.FileInfoDto;
import ru.itis.javalab.models.FileInfo;
import ru.itis.javalab.repositories.FileInfoJpaRepository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileDownloadServiceImpl implements FileDownloadService {

    @Autowired
    private FileInfoJpaRepository fileInfoJpaRepository;

    private StringBuilder pathToSave = new StringBuilder();

    @Override
    public FileInfoDto saveFileToStorage(FileInfoDto fileInfoDto) {
        FileInfo fileInfo = FileInfo.builder()
                .name("input")
                .size(fileInfoDto.getSize())
                .contentType(fileInfoDto.getContentType())
                .pathToFile("C://files")
                .build();

        try {
            fileInfoJpaRepository.save(fileInfo);
            pathToSave.append(fileInfo.getPathToFile()).append(File.separator).append(fileInfo.getId()).append(File.separator);
            Path path = Paths.get(pathToSave.toString());
            Files.createDirectory(path);
            File file = new File(pathToSave.toString()+"output.json");
            file.createNewFile();
            pathToSave.append(fileInfo.getName()).append(".").append(fileInfo.getContentType().split("/")[1]);
            Files.copy(fileInfoDto.getFile(), Paths.get(pathToSave.toString()));

        } catch (IOException e){
            fileInfoJpaRepository.delete(fileInfo);
            throw new IllegalArgumentException(e);
        }
        return FileInfoDto.from(fileInfo);
    }
}

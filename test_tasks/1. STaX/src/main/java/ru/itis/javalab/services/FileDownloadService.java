package ru.itis.javalab.services;

import ru.itis.javalab.dto.FileInfoDto;
import java.util.Optional;

public interface FileDownloadService {
    FileInfoDto saveFileToStorage(FileInfoDto fileInfoDto);
}

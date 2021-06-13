package ru.itis.javalab.services;

import java.io.InputStream;

public interface VideoUploadService {
        void saveFileToStorage(InputStream file, String submittedFileName,String contentType,Long size);
}

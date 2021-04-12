package ru.itis.javalab.services;

import ru.itis.javalab.models.FileInfo;

import java.io.IOException;

public interface Xml2JsonConverter {
    void convertXmlToJson(FileInfo fileInfo) throws IOException;
}

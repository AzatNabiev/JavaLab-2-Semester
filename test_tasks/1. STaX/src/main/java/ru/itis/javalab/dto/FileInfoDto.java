package ru.itis.javalab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.models.FileInfo;

import java.io.InputStream;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileInfoDto {
    private Long id;
    private InputStream file;
    private String contentType;
    private Long size;

    public static FileInfoDto from(FileInfo fileInfo){
        return FileInfoDto.builder().id(fileInfo.getId()).contentType(fileInfo.getContentType())
                .size(fileInfo.getSize()).build();
    }
}

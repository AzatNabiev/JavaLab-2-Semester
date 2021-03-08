package ru.itis.javalab.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class VideoInfo {
    private Long id;
    private String originalFileName;
    private String storageFileName;
    private String type;
    private Long size;
}

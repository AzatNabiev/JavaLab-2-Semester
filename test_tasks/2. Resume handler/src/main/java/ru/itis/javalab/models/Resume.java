package ru.itis.javalab.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Builder
@Document(collection="resumes")
public class Resume {
    @Id
    private String _id;
    private String firstName;
    private String lastName;
    private String email;
    private String telegram;
    private Long age;
    private String aboutYourself;
}

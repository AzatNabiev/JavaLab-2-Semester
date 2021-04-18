package ru.itis.javalab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.itis.javalab.models.Resume;

import javax.validation.constraints.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeDto {
    private String _id;
    @NotEmpty
    @Length(min=2, max=20)
    private String firstName;
    @NotEmpty
    @Length(min = 2, max = 20)
    private String lastName;
    @NotEmpty
    @Email
    @Length(min=6, max=40)
    private String email;
    @NotEmpty
    @Length(min=6, max=40)
    private String telegram;
    @Min(value = 14, message = "too young")
    @Max(value = 70, message = "too old")
    private Long age;
    @Length(max = 255, message = "too much words")
    private String aboutYourself;

    public static ResumeDto from(Resume resume){
        return ResumeDto.builder()._id(resume.get_id()).firstName(resume.getFirstName())
                .lastName(resume.getLastName()).age(resume.getAge()).email(resume.getEmail())
                .telegram(resume.getTelegram()).aboutYourself(resume.getAboutYourself()).build();
    }
    public static List<ResumeDto> from(List<Resume> resumes){
        return resumes.stream().map(ResumeDto::from).collect(Collectors.toList());
    }

}

package ru.itis.javalab.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class UserSignUpForm {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}

package ru.itis.javalab.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class UserSignInForm {
    private String email;
    private String password;
}

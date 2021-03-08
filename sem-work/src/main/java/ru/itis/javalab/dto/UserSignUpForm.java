package ru.itis.javalab.dto;

import lombok.*;
import ru.itis.javalab.validation.ValidNames;
import ru.itis.javalab.validation.ValidPassword;

import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@ValidNames(
        message = "{errors.invalid.names}",
        name = "firstName",
        surname="lastName"
)
public class UserSignUpForm {
    @Email(message = "{errors.incorrect.email}")
    private String email;
    private String firstName;
    private String lastName;
    @ValidPassword(message = "{errors.invalid.password}")
    private String password;
}

package ru.itis.javalab.dto;

import lombok.*;
import ru.itis.javalab.validation.ValidPassword;
import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class UserSignInForm {
    @Email(message = "{errors.incorrect.email}")
    private String email;
    @ValidPassword(message = "{errors.invalid.password}")
    private String password;
}

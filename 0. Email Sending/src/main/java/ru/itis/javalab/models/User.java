package ru.itis.javalab.models;

import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @Enumerated(value = EnumType.STRING)
    private State state;

    private String confirmCode;

    public enum State{
        CONFIRMED, NOT_CONFIRMED
    }
}

package ru.itis.javalab.models;

import lombok.*;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String hashPassword;
    private String confirmCode;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    public enum Status {
        CONFIRMED, NOT_CONFIRMED
    }

    @Enumerated(value = EnumType.STRING)
    private State state;
    public enum State {
        ACTIVE, BANNED
    }

    @Enumerated(value = EnumType.STRING)
    private Role role;
    public enum Role {
        USER, ADMIN
    }

    public boolean isActive() {
        return this.state == State.ACTIVE;
    }

    public boolean isBanned() {
        return this.state == State.BANNED;
    }

    public boolean isAdmin() {
        return this.role == Role.ADMIN;
    }

}

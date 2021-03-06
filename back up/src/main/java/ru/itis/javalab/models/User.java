package ru.itis.javalab.models;

import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString(exclude = {"doctors","diseases"})
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Long age;
    @ManyToMany(mappedBy = "users")
    private List<Doctor> doctors;

    @ManyToMany(mappedBy = "sicked")
    private List<Disease> diseases;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public enum State {
        ACTIVE, BANNED
    }

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

package ru.itis.javalab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.models.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String name;
    private String login;

    public static UserDto from(User user){
        return UserDto.builder().login(user.getEmail())
                .name(user.getName())
                .build();
    }

    public static List<UserDto> from(List<User> users){
       return users.stream().map(UserDto::from).collect(Collectors.toList());
    }
}

package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.exception.AlreadyExistUserException;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        User user = User.builder().email(userDto.getLogin())
                .name(userDto.getName())
                .build();
        try {
            userRepository.save(user);
        } catch (Exception ex){
            throw new AlreadyExistUserException("User already exists");
        }
        return UserDto.from(user);
    }
}

package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.UserSignInForm;
import ru.itis.javalab.repositories.UsersRepository;

@Service
public class SignInServiceImpl implements SignInService {

    @Autowired
    private BCryptService bCryptService;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public boolean checkEmailAndPass(UserSignInForm user) {
        return false;
    }
}

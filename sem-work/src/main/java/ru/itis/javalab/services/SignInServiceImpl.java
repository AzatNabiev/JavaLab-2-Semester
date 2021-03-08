package ru.itis.javalab.services;

import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.UserSignInForm;

@Service
public class SignInServiceImpl implements SignInService {

    //место для юзер репозитория

    @Override
    public boolean checkEmailAndPass(UserSignInForm user) {
        return false;
    }
}

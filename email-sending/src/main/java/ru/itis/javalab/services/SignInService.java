package ru.itis.javalab.services;

import ru.itis.javalab.dto.UserSignInForm;

public interface SignInService {
    boolean checkEmailAndPass(UserSignInForm user);

}

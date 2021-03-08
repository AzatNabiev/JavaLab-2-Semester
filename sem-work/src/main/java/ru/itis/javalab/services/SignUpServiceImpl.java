package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.UserSignUpForm;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;
import ru.itis.javalab.util.EmailUtil;
import ru.itis.javalab.util.MailsGenerator;

import java.util.UUID;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private MailsGenerator mailsGenerator;

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private BCryptService bCryptService;

    @Autowired
    private UsersRepository usersRepository;

    @Value("${server.url}")
    private String serverUrl;

    @Value("${mail.theme}")
    private String mailTheme;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void signUp(UserSignUpForm userForm) {
        User user = User.builder()
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .password(bCryptService.cryptPassword(userForm.getPassword()))
                .email(userForm.getEmail())
                .state(User.State.NOT_CONFIRMED)
                .confirmCode(UUID.randomUUID().toString())
                .build();
        usersRepository.save(user);
        String confirmMail = mailsGenerator.getMailForConfirm(serverUrl, user.getConfirmCode());
        emailUtil.sendMail(user.getEmail(), mailTheme, from, confirmMail);
    }

    @Override
    public void confirmUser(String confirmCode) {
        //TODO:add confirmed using confirmCode
    }
}

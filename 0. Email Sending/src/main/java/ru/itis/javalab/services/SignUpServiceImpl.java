package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.UserSignUpForm;
import ru.itis.javalab.models.User;
import ru.itis.javalab.util.EmailUtil;
import ru.itis.javalab.util.MailsGenerator;

import java.util.UUID;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    MailsGenerator mailsGenerator;

    @Autowired
    EmailUtil emailUtil;

    @Value("${server.url}")
    private String serverUrl;

    @Value("${mail.theme}")
    private String mailTheme;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void signUp(UserSignUpForm userForm) {
        User user=User.builder()
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .password(userForm.getPassword())
                .email(userForm.getEmail())
                .confirmCode(UUID.randomUUID().toString())
                .build();
        //метод для сохранения юзера в бд
        String confirmMail = mailsGenerator.getMailForConfirm(serverUrl,user.getConfirmCode());
        System.out.println("lol");
        emailUtil.sendMail(user.getEmail(), mailTheme, from,confirmMail);
    }
}

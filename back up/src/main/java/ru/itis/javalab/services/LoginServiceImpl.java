package ru.itis.javalab.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.EmailPasswordDto;
import ru.itis.javalab.dto.TokenDto;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UserRepository;

import java.util.Calendar;
import java.util.Date;

@Service
public class LoginServiceImpl implements LoginService {

    private User user;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.refreshExpirationDateInMs}")
    private String refreshExpirationDateInMs;


    @Override
    public TokenDto login(EmailPasswordDto emailPassword) {

        try {
            user=userRepository.findByEmail(emailPassword.getEmail());
        } catch (UsernameNotFoundException ex){
            throw new UsernameNotFoundException("User not found");
        }
        if (passwordEncoder.matches(emailPassword.getPassword(),user.getPassword())){
            String tokenValue = Jwts.builder()
                    .setSubject(user.getId().toString())
                    .claim("name", user.getFirstName())
                    .claim("role", user.getRole().name())
//                    .setIssuedAt(new Date(System.currentTimeMillis()))
//                    .setExpiration(new Date(System.currentTimeMillis()+refreshExpirationDateInMs))
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .compact();

            return TokenDto.builder()
                    .token(tokenValue)
                    .build();
        } else {
            throw new UsernameNotFoundException("Invalid user name or password");
        }
    }
}

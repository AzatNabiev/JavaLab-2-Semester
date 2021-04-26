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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    @Value("${jwt.accessExpirationDateInMs}")
    private String accessExpirationDateInMs;

    @SuppressWarnings( "deprecation" )
    @Override
    public Map<String,TokenDto> login(EmailPasswordDto emailPassword) {

        try {
            user=userRepository.findByEmail(emailPassword.getEmail());
        } catch (UsernameNotFoundException ex){
            throw new UsernameNotFoundException("User not found");
        }
        if (passwordEncoder.matches(emailPassword.getPassword(),user.getPassword())){

            TokenDto accessToken = getAccessToken(user);
            TokenDto refreshToken = getRefreshToken(user);
            Map<String, TokenDto> map = new HashMap<>();
            map.put("accessToken",accessToken);
            map.put("refreshToken",refreshToken);
            return map;
        } else {
            throw new UsernameNotFoundException("Invalid user name or password");
        }
    }
    @Override
    public TokenDto getAccessToken(User user){
        String access=Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("name", user.getFirstName())
                .claim("role", user.getRole().name())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+Long.parseLong(accessExpirationDateInMs)))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        return TokenDto.builder().token(access).build();
    }
    @Override
    public TokenDto getRefreshToken(User user){
        String refresh = Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("name", user.getFirstName())
                .claim("role", user.getRole().name())
                .claim("email",user.getEmail())
                .claim("role",user.getRole().toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+Long.parseLong(refreshExpirationDateInMs)))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        return TokenDto.builder().token(refresh).build();
    }

}

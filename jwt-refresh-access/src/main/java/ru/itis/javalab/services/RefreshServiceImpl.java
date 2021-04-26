package ru.itis.javalab.services;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.TokenDto;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UserRepository;
import ru.itis.javalab.security.details.UserDetailsImpl;

import java.util.Optional;

@Service
public class RefreshServiceImpl implements RefreshService {

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginService loginService;

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Expired or invalid JWT token");
        }
    }

    @Override
    public TokenDto refreshed(String token) {
        String id = getId(token);
        Optional<User> user = userRepository.findById(Long.parseLong(id));
        UserDetailsImpl userDetails = new UserDetailsImpl(user.get());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        return loginService.getAccessToken(user.get());
    }
    private String getId(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

}

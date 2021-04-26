package ru.itis.javalab.security.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import ru.itis.javalab.models.User;
import ru.itis.javalab.security.details.UserDetailsImpl;
import ru.itis.javalab.services.RefreshService;
import ru.itis.javalab.services.UserService;

import java.util.Optional;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {


    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private UserService userService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getName();
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJwt(token)
                    .getBody();
        } catch (Exception e){
            throw new AuthenticationCredentialsNotFoundException("Bad token");
        }
        User user = User.builder().email(claims.get("email", String.class))
                .password(claims.get("password",String.class)).build();
        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        authentication.setAuthenticated(true);
        ((JwtAuthentication)authentication).setUserDetails(userDetails);
        return authentication;

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return JwtAuthentication.class.equals(aClass);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Expired or invalid JWT token");
        }
    }
    public Authentication getAuthentication(String token) {
        Optional<User> user = userService.findById(Long.parseLong(getUserEmail(token)));
        UserDetailsImpl userDetails = new UserDetailsImpl(user.get());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
    public String getUserEmail(String token) {
        System.out.println(Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject());
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
}

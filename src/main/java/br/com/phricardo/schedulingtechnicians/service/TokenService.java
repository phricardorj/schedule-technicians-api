package br.com.phricardo.schedulingtechnicians.service;

import br.com.phricardo.schedulingtechnicians.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${app.jwt.token.secret}")
    private String JWT_SECURITY_KEY;

    @Value("${app.jwt.token.issuer}")
    private String JWT_ISSUER;

    @Value("${app.jwt.expiration-time-in-hours}")
    private Integer JWT_EXPIRATION_TIME_IN_HOURS;

    public String generate(@NonNull User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECURITY_KEY);
            return JWT.create()
                    .withIssuer(JWT_ISSUER)
                    .withSubject(user.getLogin())
                    .withClaim("id", user.getId())
                    .withExpiresAt(dateExpiresAt())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error generating JWT token", exception);
        }
    }

    private Instant dateExpiresAt() {
        return LocalDateTime.now().plusHours(JWT_EXPIRATION_TIME_IN_HOURS).toInstant(ZoneOffset.of("-03:00"));
    }

    public String validateAndGetSubject(String token) {
        Algorithm algorithm = Algorithm.HMAC256(JWT_SECURITY_KEY);
        return JWT.require(algorithm)
                .withIssuer(JWT_ISSUER)
                .build()
                .verify(token)
                .getSubject();
    }
}
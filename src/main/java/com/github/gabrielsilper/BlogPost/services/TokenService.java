package com.github.gabrielsilper.BlogPost.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {
    private final Algorithm algorithm;

    public TokenService(@Value("${api.security.token.secret}") String secret) {
        this.algorithm = Algorithm.HMAC256(secret);
    }

    public String generateToken(String username) {
        return JWT.create()
                .withIssuer("BlogPost")
                .withSubject(username)
                .withExpiresAt(generateExpirationDate())
                .sign(algorithm);
    }

    private Instant generateExpirationDate() {
        return Instant.now()
                .plus(2, ChronoUnit.HOURS);
    }

    public String validateToken(String token) {
        return JWT.require(algorithm)
                .withIssuer("BlogPost")
                .build()
                .verify(token)
                .getSubject();
    }
}

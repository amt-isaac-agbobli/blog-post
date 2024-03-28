package com.isaac.blogpost.jwt.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.isaac.blogpost.entity.User;
import com.isaac.blogpost.exception.HttpException;
import com.isaac.blogpost.jwt.JwtService;
import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class JwtServiceImpl implements JwtService {

    @Value("${api.security.token.secret}")
    private String secret;

    @Value("${api.security.token.issuer}")
    private String issuer;
    @Override
    public String extractSubject(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
          throw new HttpException("Invalid token", 400);
        }
    }

    @Override
    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create().withIssuer(issuer)
                .withSubject(user.getEmail())
                .withClaim("id", user.getId())
                .withExpiresAt(getExpirationDate())
                .sign(algorithm);
    }
    private Instant getExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}

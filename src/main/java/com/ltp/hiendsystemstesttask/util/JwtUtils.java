package com.ltp.hiendsystemstesttask.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class JwtUtils {

    @Value("${testtask.jwt.secret}")
    private String secret;
    @Value("${testtask.jwt.lifetime}")
    private long lifetime;
    @Value("${testtask.jwt.issuer}")
    private String issuer;
    private Algorithm algorithm;
    private JWTVerifier jwtVerifier;

    public String generateToken(final String username) {
        try {
            final String token = JWT.create()
                    .withSubject(username)
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(Instant.now().plusSeconds(lifetime))
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException e) {
            e.printStackTrace();
            return new String();
        }
    }

    public Optional<DecodedJWT> verifyAndDecode(final String token) {
        try {
            final DecodedJWT decodedJWT = jwtVerifier.verify(token);
            return Optional.of(decodedJWT);
        }catch(JWTVerificationException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @PostConstruct
    private void init() {
        algorithm = Algorithm.HMAC256(secret);
        jwtVerifier = JWT.require(algorithm)
                .build();
    }

}

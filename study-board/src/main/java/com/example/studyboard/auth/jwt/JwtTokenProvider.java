package com.example.studyboard.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    private final String secret;
    private final int expirySeconds;

    public JwtTokenProvider(@Value("${jwt.secret}") String secret, @Value("${jwt.expirySeconds}") int expirySeconds) {
        this.secret = secret;
        this.expirySeconds = expirySeconds;
    }

    public String createToken(Principal principal) {
        Date now = new Date();
        return JWT.create()
                .withSubject(principal.getUsername())
                .withIssuedAt(now)
                .withExpiresAt(new Date(now.getTime() + expirySeconds * 1000L))
                .withClaim("id", principal.getUser().getId())
                .withClaim("userId", principal.getUsername())
                .sign(Algorithm.HMAC512(secret));
    }

    public String extractUserId(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaim("userId").asString();
    }

    public DecodedJWT verify(String token){
        return JWT.require(Algorithm.HMAC512(secret)).build().verify(token);
    }
}

package com.alejandromax.token.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {
    private static final String SECRET_KEY = "jQ7^yU3*mB9!vN5(cX1@zA4#sD8$fG6%";
    private static final long EXPLORATION_TIME = 1000 * 60 * 60; // 1 Hora para la expiración del Token

    public String generateToken(String username) {
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        String token = Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPLORATION_TIME))
                .signWith(key)
                .compact();

        return token;
    }
}
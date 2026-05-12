package com.example.jwtpoc.security;

import com.example.jwtpoc.user.AppUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Creates JWT access tokens and validates tokens sent back by clients.
 */
@Service
public class JwtService {

    private final SecretKey secretKey;
    private final long expirationMinutes;

    public JwtService(
            @Value("${app.jwt.secret}") String secret,
            @Value("${app.jwt.expiration-minutes}") long expirationMinutes) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationMinutes = expirationMinutes;
    }

    /**
     * Creates a signed JWT containing standard claims plus user-specific custom claims.
     */
    public String createToken(AppUser user) {
        Instant now = Instant.now();
        Instant expiresAt = now.plusSeconds(expirationMinutes * 60);

        return Jwts.builder()
                .subject(user.getEmail())
                .claim("userId", user.getId())
                .claim("name", user.getName())
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiresAt))
                .signWith(secretKey)
                .compact();
    }

    /**
     * Extracts the token subject. In this PoC the subject is the user's email.
     */
    public String extractSubject(String token) {
        try {
            return extractClaims(token).getSubject();
        } catch (RuntimeException ex) {
            return null;
        }
    }

    /**
     * Validates token signature, expiry, and subject-user match.
     */
    public boolean isTokenValid(String token, AppUser user) {
        try {
            Claims claims = extractClaims(token);
            return user.getEmail().equalsIgnoreCase(claims.getSubject())
                    && claims.getExpiration().after(new Date());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    /**
     * Returns the configured token lifetime used in API responses.
     */
    public long expirationMinutes() {
        return expirationMinutes;
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}

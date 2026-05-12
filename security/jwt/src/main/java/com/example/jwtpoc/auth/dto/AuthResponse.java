package com.example.jwtpoc.auth.dto;

import com.example.jwtpoc.user.AppUser;

/**
 * Response returned after successful register/login, including the JWT access token.
 */
public record AuthResponse(
        UserResponse user,
        String accessToken,
        String tokenType,
        long expiresInMinutes) {

    /**
     * Builds a response DTO without exposing the stored password hash.
     */
    public static AuthResponse from(AppUser user, String accessToken, long expiresInMinutes) {
        return new AuthResponse(UserResponse.from(user), accessToken, "Bearer", expiresInMinutes);
    }
}

package com.example.jwtpoc.auth.dto;

import com.example.jwtpoc.user.AppUser;

/**
 * Public user data returned to the client. Password hashes are never included.
 */
public record UserResponse(
        Long id,
        String name,
        String email) {

    /**
     * Converts the internal user object to a safe API response.
     */
    public static UserResponse from(AppUser user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }
}

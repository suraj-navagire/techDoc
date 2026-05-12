package com.example.jwtpoc.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Registration request used to create a new user before issuing a JWT.
 */
public record RegisterRequest(
        @NotBlank String name,
        @Email @NotBlank String email,
        @Size(min = 8, message = "password must be at least 8 characters") String password) {
}

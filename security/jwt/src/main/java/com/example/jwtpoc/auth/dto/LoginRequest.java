package com.example.jwtpoc.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Login request containing credentials used to authenticate a user.
 */
public record LoginRequest(
        @Email @NotBlank String email,
        @NotBlank String password) {
}

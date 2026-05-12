package com.example.jwtpoc.todo.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Request body for creating a protected todo.
 */
public record CreateTodoRequest(@NotBlank String title) {
}

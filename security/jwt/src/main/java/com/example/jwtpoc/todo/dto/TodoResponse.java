package com.example.jwtpoc.todo.dto;

import com.example.jwtpoc.todo.Todo;
import java.time.Instant;

/**
 * Todo data returned from protected todo APIs.
 */
public record TodoResponse(
        Long id,
        String title,
        boolean completed,
        Instant createdAt) {

    /**
     * Converts the internal todo object to an API response.
     */
    public static TodoResponse from(Todo todo) {
        return new TodoResponse(
                todo.getId(),
                todo.getTitle(),
                todo.isCompleted(),
                todo.getCreatedAt());
    }
}

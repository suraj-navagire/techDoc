package com.example.jwtpoc.todo;

import com.example.jwtpoc.user.AppUser;
import java.time.Instant;

/**
 * Simple protected resource owned by an authenticated user.
 */
public class Todo {

    private final Long id;

    private final String title;

    private final boolean completed;

    private final Instant createdAt;

    private final AppUser owner;

    public Todo(Long id, String title, AppUser owner) {
        this.id = id;
        this.title = title;
        this.completed = false;
        this.createdAt = Instant.now();
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public AppUser getOwner() {
        return owner;
    }
}

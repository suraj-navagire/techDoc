package com.example.jwtpoc.user;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

/**
 * In-memory user repository used instead of a real database for this JWT PoC.
 */
@Repository
public class AppUserRepository {

    // Consider this ConcurrentHashMap as the database for this JWT learning PoC.
    private final Map<String, AppUser> usersByEmail = new ConcurrentHashMap<>();
    private final AtomicLong userIdSequence = new AtomicLong(1);

    /**
     * Stores a new user and assigns a simple incrementing id.
     */
    public AppUser save(String name, String email, String password) {
        AppUser user = new AppUser(userIdSequence.getAndIncrement(), name, normalizeEmail(email), password);
        usersByEmail.put(user.getEmail(), user);
        return user;
    }

    /**
     * Finds a user by email without requiring exact case matching.
     */
    public Optional<AppUser> findByEmailIgnoreCase(String email) {
        return Optional.ofNullable(usersByEmail.get(normalizeEmail(email)));
    }

    /**
     * Checks whether an email is already registered.
     */
    public boolean existsByEmailIgnoreCase(String email) {
        return usersByEmail.containsKey(normalizeEmail(email));
    }

    private String normalizeEmail(String email) {
        return email.toLowerCase();
    }
}

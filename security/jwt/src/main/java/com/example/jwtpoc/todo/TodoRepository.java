package com.example.jwtpoc.todo;

import com.example.jwtpoc.user.AppUser;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

/**
 * In-memory todo repository used instead of a real database for this JWT PoC.
 */
@Repository
public class TodoRepository {

    // Consider this ConcurrentHashMap as the database for this JWT learning PoC.
    private final Map<Long, Todo> todosById = new ConcurrentHashMap<>();
    private final AtomicLong todoIdSequence = new AtomicLong(1);

    /**
     * Stores a todo owned by the authenticated user.
     */
    public Todo save(String title, AppUser owner) {
        Todo todo = new Todo(todoIdSequence.getAndIncrement(), title, owner);
        todosById.put(todo.getId(), todo);
        return todo;
    }

    /**
     * Returns todos for one user, newest first.
     */
    public List<Todo> findAllByOwnerIdOrderByIdDesc(Long ownerId) {
        return todosById.values()
                .stream()
                .filter(todo -> todo.getOwner().getId().equals(ownerId))
                .sorted((left, right) -> right.getId().compareTo(left.getId()))
                .toList();
    }
}

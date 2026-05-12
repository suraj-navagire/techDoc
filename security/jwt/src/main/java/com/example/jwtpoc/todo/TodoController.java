package com.example.jwtpoc.todo;

import com.example.jwtpoc.todo.dto.CreateTodoRequest;
import com.example.jwtpoc.todo.dto.TodoResponse;
import com.example.jwtpoc.user.AppUser;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sample protected REST API. Requests reach this controller only after JWT
 * authentication succeeds.
 */
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    /**
     * Lists todos owned by the currently authenticated user.
     */
    @GetMapping
    public Map<String, List<TodoResponse>> list(AppUser user) {
        List<TodoResponse> todos = todoRepository.findAllByOwnerIdOrderByIdDesc(user.getId())
                .stream()
                .map(TodoResponse::from)
                .toList();

        return Map.of("todos", todos);
    }

    /**
     * Creates a todo for the currently authenticated user.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, TodoResponse> create(
            AppUser user,
            @Valid @RequestBody CreateTodoRequest request) {
        Todo todo = todoRepository.save(request.title().trim(), user);
        return Map.of("todo", TodoResponse.from(todo));
    }
}

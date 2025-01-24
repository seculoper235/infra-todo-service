package com.example.infratestapi.web.controller;

import com.example.infratestapi.model.todo.TodoItemInfo;
import com.example.infratestapi.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<List<TodoItemInfo>> find() {
        List<TodoItemInfo> result = todoService.find();

        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody TodoCreateRequest todoCreateRequest) {
        TodoItemInfo result = todoService.register(todoCreateRequest.title());

        return ResponseEntity.created(URI.create("/api/todo/" + result.id())).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<TodoItemInfo> update(@PathVariable Long id, @RequestBody TodoCreateRequest todoCreateRequest) {
        TodoItemInfo result = todoService.update(id, todoCreateRequest.title());

        return ResponseEntity.ok(result);
    }

    @PutMapping("{id}/status")
    public ResponseEntity<TodoItemInfo> changeStatus(@PathVariable Long id, @RequestBody TodoStatusRequest statusRequest) {
        TodoItemInfo result = todoService.changeStatus(id, statusRequest.completed());

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        todoService.delete(id);

        return ResponseEntity.noContent().build();
    }
}

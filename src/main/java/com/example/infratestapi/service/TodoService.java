package com.example.infratestapi.service;

import com.example.infratestapi.domain.todo.TodoItem;
import com.example.infratestapi.model.todo.TodoItemInfo;
import com.example.infratestapi.persistence.TodoItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoItemRepository todoItemRepository;

    public List<TodoItemInfo> find() {
        return todoItemRepository.findAll().stream()
                .map(TodoItem::toModel).toList();
    }

    public TodoItemInfo register(String title) {
        TodoItem todoItem = TodoItem.builder()
                .title(title)
                .completed(false)
                .build();

        return todoItemRepository.save(todoItem).toModel();
    }

    @Transactional
    public TodoItemInfo update(Long id, String title) {
        TodoItem todoItem = todoItemRepository.getTodoItemById(id);

        todoItem.updateContent(title);

        return todoItem.toModel();
    }

    @Transactional
    public TodoItemInfo changeStatus(Long id, boolean completed) {
        TodoItem todoItem = todoItemRepository.getTodoItemById(id);

        todoItem.changeStatus(completed);

        return todoItem.toModel();
    }
}

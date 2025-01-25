package com.example.infratestapi.persistence;

import com.example.infratestapi.domain.todo.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
    default TodoItem getTodoItemById(Long id) {
        return findById(id).orElseThrow(() -> new RuntimeException("존재하지 않는 투두 아이템입니다"));
    }
}

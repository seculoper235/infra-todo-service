package com.example.infratestapi.domain.todo;

import com.example.infratestapi.model.todo.TodoItemInfo;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.type.NumericBooleanConverter;

@Entity
@Table(name = "TODO_ITEM")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TodoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Convert(converter = NumericBooleanConverter.class)
    @Builder.Default
    private Boolean completed = false;

    public void updateContent(String title) {
        this.title = title;
    }

    public void changeStatus(Boolean completed) {
        this.completed = completed;
    }

    public TodoItemInfo toModel() {
        return new TodoItemInfo(id, title, completed);
    }
}

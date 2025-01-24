package com.example.infratestapi.model.todo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public record TodoItemInfo(
        Long id,
        String title,
        Boolean completed
) {
}

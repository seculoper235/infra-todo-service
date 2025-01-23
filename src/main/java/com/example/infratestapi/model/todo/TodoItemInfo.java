package com.example.infratestapi.model.todo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public record TodoItemInfo(
        Integer order,
        String title,
        Boolean completed
) {
}

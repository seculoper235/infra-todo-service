package com.example.infratestapi.web.controller;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public record TodoCreateRequest(
        String title
) {
}

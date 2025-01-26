package com.example.infratestapi.controller;

import com.example.infratestapi.model.todo.TodoItemInfo;
import com.example.infratestapi.service.TodoService;
import com.example.infratestapi.web.controller.TodoController;
import com.example.infratestapi.web.controller.TodoCreateRequest;
import com.example.infratestapi.web.controller.TodoStatusRequest;
import com.example.infratestapi.web.security.SecurityConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest(value = TodoController.class,
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {SecurityConfig.class}),
        })
public class TodoItemAPITest {
    @Autowired
    protected MockMvc mockMvc;

    @MockitoBean
    private TodoService todoService;

    @Autowired
    protected ObjectMapper objectMapper;

    private final List<TodoItemInfo> expected = List.of(
            new TodoItemInfo(1L, "first todo", false)
    );

    @Test
    @DisplayName("TodoItem 조회 시, 아이템 목록이 반환된다")
    void find_todo_item_return_item_list() throws Exception {

        given(todoService.find()).willReturn(expected);

        mockMvc.perform(get("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("TodoItem 등록 시, 201 코드와 URI가 반환된다")
    void register_todo_item_return_201_uri() throws Exception {
        URI expected = URI.create("/api/todo/" + 2);

        TodoCreateRequest request = new TodoCreateRequest("second todo");
        TodoItemInfo result = new TodoItemInfo(2L, "second todo", false);

        given(todoService.register(any())).willReturn(result);

        mockMvc.perform(post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", equalTo(expected.toString())));
    }

    @Test
    @DisplayName("TodoItem 수정 시, 수정된 아이템 정보가 반환된다")
    void update_todo_item_update_item_info() throws Exception {
        TodoCreateRequest request = new TodoCreateRequest("update todo");
        TodoItemInfo result = new TodoItemInfo(1L, "update todo", false);

        given(todoService.update(anyLong(), anyString())).willReturn(result);

        mockMvc.perform(put("/api/todo/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(result.id().intValue())))
                .andExpect(jsonPath("$.title", equalTo(result.title())))
                .andExpect(jsonPath("$.completed", equalTo(result.completed())));
    }

    @Test
    @DisplayName("TodoItem 완료/미완료 체크 시, 아이템의 상태가 바뀐다")
    void check_todo_item_status_update_completed_status() throws Exception {
        TodoStatusRequest request = new TodoStatusRequest(true);
        TodoItemInfo result = new TodoItemInfo(2L, "second todo", true);

        given(todoService.changeStatus(anyLong(), anyBoolean())).willReturn(result);

        mockMvc.perform(put("/api/todo/{id}/status", 2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(result.id().intValue())))
                .andExpect(jsonPath("$.title", equalTo(result.title())))
                .andExpect(jsonPath("$.completed", equalTo(result.completed())));
    }
}

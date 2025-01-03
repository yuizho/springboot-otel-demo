package dev.yuizho.springhtmlx.controllers;

import dev.yuizho.springhtmlx.applications.TodoService;
import dev.yuizho.springhtmlx.domain.Todo;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("backend")
public class TodoBackendController {
    Logger LOGGER = LoggerFactory.getLogger(TodoBackendController.class);

    private final TodoService todoService;

    public TodoBackendController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> index(HttpServletRequest request) {
        LOGGER.info(
                "TodoBackendController#index() traceparent: {}, tracestate: {}",
                request.getHeader("traceparent"),
                request.getHeader("tracestate")
        );

        return todoService.findAll();
    }

    @PostMapping("/register")
    public void register(HttpServletRequest request, @RequestParam(value = "todo") String todo) {
        LOGGER.info(
                "TodoBackendController#register() traceparent: {}, tracestate: {}, todo: {}",
                request.getHeader("traceparent"),
                request.getHeader("tracestate"),
                todo
        );

        todoService.register(todo);
    }

    @PostMapping("/delete")
    public void delete(HttpServletRequest request, @RequestParam(value = "id") int id) {
        LOGGER.info(
                "TodoBackendController#delete() traceparent: {}, tracestate: {}, id: {}",
                request.getHeader("traceparent"),
                request.getHeader("tracestate"),
                id
        );

        todoService.delete(id);
    }
}

package dev.yuizho.springhtmlx.controllers;

import dev.yuizho.springhtmlx.applications.TodoService;
import dev.yuizho.springhtmlx.domain.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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
    public List<Todo> index() {
        LOGGER.info("TodoBackendController#index()");

        return todoService.findAll();
    }

    @PostMapping("/register")
    public void register(@RequestParam(value = "todo") String todo) {
        LOGGER.info("TodoBackendController#register() todo: {}", todo);

        todoService.register(todo);
    }

    @PostMapping("/delete")
    public void delete(@RequestParam(value = "id") int id) {
        LOGGER.info("TodoBackendController#delete() id: {}", id);

        todoService.delete(id);
    }
}

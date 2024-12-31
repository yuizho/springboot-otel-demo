package dev.yuizho.springhtmlx.controllers;

import dev.yuizho.springhtmlx.applications.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("todo")
public class TodoController {
    Logger LOGGER = LoggerFactory.getLogger(TodoController.class);

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public String index(Model model) {
        LOGGER.info("TodoController#index()");

        model.addAttribute(
                "todoList",
                todoService.findAll()
        );
        return "todo/index";
    }

    @PostMapping("/register")
    public String register(Model model, String todo) {
        LOGGER.info("TodoController#register() todo: {}", todo);

        todoService.register(todo);

        model.addAttribute(
                "todoList",
                todoService.findAll()
        );
        model.addAttribute("form", "");
        return "todo/todolist";
    }

    @PostMapping("/delete")
    public String delete(Model model, int id) {
        LOGGER.info("TodoController#delete() id: {}", id);

        todoService.delete(id);

        model.addAttribute(
                "todoList",
                todoService.findAll()
        );
        return "todo/todolist";
    }
}

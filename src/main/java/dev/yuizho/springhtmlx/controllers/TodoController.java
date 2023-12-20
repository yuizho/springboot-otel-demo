package dev.yuizho.springhtmlx.controllers;

import dev.yuizho.springhtmlx.applications.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("todo")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute(
                "todoList",
                todoService.findAll()
        );
        return "todo/index";
    }

    @PostMapping("/register")
    public String register(Model model, String todo) {
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
        todoService.delete(id);

        model.addAttribute(
                "todoList",
                todoService.findAll()
        );
        return "todo/todolist";
    }
}

package dev.yuizho.springhtmlx.applications;

import dev.yuizho.springhtmlx.domain.Todo;
import dev.yuizho.springhtmlx.domain.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public void register(String todo) {
        todoRepository.save(todo);
    }

    public void delete(int id) {
        todoRepository.delete(id);
    }
}

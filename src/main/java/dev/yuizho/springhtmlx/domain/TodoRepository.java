package dev.yuizho.springhtmlx.domain;

import java.util.List;

public interface TodoRepository {
    List<Todo> findAll();
    int save(String name);
    int delete(int id);
}

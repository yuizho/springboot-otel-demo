package dev.yuizho.springhtmlx.controllers;

import dev.yuizho.springhtmlx.configs.BackendProperties;
import dev.yuizho.springhtmlx.domain.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("todo")
public class TodoController {
    Logger LOGGER = LoggerFactory.getLogger(TodoController.class);

    private final RestTemplate restTemplate;
    private final BackendProperties backendProperties;

    public TodoController(BackendProperties backendProperties, RestTemplateBuilder restTemplateBuilder) {
        this.backendProperties = backendProperties;
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping
    public String index(Model model) {
        LOGGER.info("TodoController#index() {}", backendProperties.hostName());
        model.addAttribute(
                "todoList",
                findAll()
        );
        return "todo/index";
    }

    @PostMapping("/register")
    public String register(Model model, String todo) {
        LOGGER.info("TodoController#register() todo: {}", todo);

        restTemplate.postForEntity(
                getBackendUrl() + "/backend/register",
                createHttpEntity("todo", todo),
                Void.class
        );

        model.addAttribute(
                "todoList",
                findAll()
        );
        model.addAttribute("form", "");
        return "todo/todolist";
    }

    @PostMapping("/delete")
    public String delete(Model model, int id) {
        LOGGER.info("TodoController#delete() id: {}", id);

        restTemplate.postForEntity(
                getBackendUrl() + "/backend/delete",
                createHttpEntity("id", String.valueOf(id)),
                Void.class
        );

        model.addAttribute(
                "todoList",
                findAll()
        );
        return "todo/todolist";
    }

    private List<Todo> findAll() {
        return restTemplate.exchange(
                getBackendUrl() + "/backend",
                org.springframework.http.HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Todo>>() {}
        ).getBody();
    }

    private String getBackendUrl() {
        return "http://" + backendProperties.hostName() + ":" + backendProperties.port();
    }

    private HttpEntity<MultiValueMap<String, String>> createHttpEntity(String key, String value) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(key, value);

        return new HttpEntity<>(map, headers);
    }
}

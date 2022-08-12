package com.careerdevs.jsonplaceholder.controllers;

import com.careerdevs.jsonplaceholder.models.TodoModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final String jsonPHTodoEndpoint = "https://jsonplaceholder.typicode.com/todos";

    @GetMapping("/all")
    public ResponseEntity<?> getAllTodos(RestTemplate restTemplate) {
        try {

            TodoModel[] response = restTemplate.getForObject(jsonPHTodoEndpoint, TodoModel[].class);

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            System.out.println(e.getClass());
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());

        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getTodoById(RestTemplate restTemplate, @PathVariable String id) {
        try {

            Integer.parseInt(id);

            System.out.println("Getting task with id: " + id);

            String url = jsonPHTodoEndpoint + "/" + id;

            TodoModel response = restTemplate.getForObject(url, TodoModel.class);

            return ResponseEntity.ok(response);

        } catch (NumberFormatException e) {

            return ResponseEntity.status(400).body("ID " + id + " was not found in the database. Must be a whole number.");

        } catch (HttpClientErrorException.NotFound e) {

            return ResponseEntity.status(404).body("User Not Found With ID: " + id);

        } catch (Exception e) {

            System.out.println(e.getClass());
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}

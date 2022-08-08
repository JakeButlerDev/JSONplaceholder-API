package com.careerdevs.jsonplaceholder.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final String jsonPlaceholderEndpoint = "https://jsonplaceholder.typicode.com/";

    @GetMapping("/photos")
    public Object photoHandler(RestTemplate restTemplate) {
        String photoEndpoint = jsonPlaceholderEndpoint;
        photoEndpoint += "photos";
        return restTemplate.getForObject(photoEndpoint, Object.class);
    }

    @GetMapping("/posts")
    public Object postsHandler(RestTemplate restTemplate) {
        String postsEndpoint = jsonPlaceholderEndpoint;
        postsEndpoint += "posts";
        return restTemplate.getForObject(postsEndpoint, Object.class);
    }

    @GetMapping("/comments")
    public Object commentsHandler(RestTemplate restTemplate) {
        String commentsEndpoint = jsonPlaceholderEndpoint;
        commentsEndpoint += "comments";
        return restTemplate.getForObject(commentsEndpoint, Object.class);
    }

    @GetMapping("/albums")
    public Object albumHandler(RestTemplate restTemplate) {
        String albumsEndpoint = jsonPlaceholderEndpoint;
        albumsEndpoint += "albums";
        return restTemplate.getForObject(albumsEndpoint, Object.class);
    }

    @GetMapping("/users")
    public Object userHandler(RestTemplate restTemplate) {
        String userEndpoint = jsonPlaceholderEndpoint;
        userEndpoint += "users";
        return restTemplate.getForObject(userEndpoint, Object.class);
    }

    @GetMapping("/todos")
    public Object todoHandler(RestTemplate restTemplate) {
        String todoEndpoint = jsonPlaceholderEndpoint;
        todoEndpoint += "todos";
        return restTemplate.getForObject(todoEndpoint, Object.class);
    }

}

package com.careerdevs.jsonplaceholder.controllers;

import com.careerdevs.jsonplaceholder.models.UserModel;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final String jsonPlaceholderEndpoint = "https://jsonplaceholder.typicode.com/users";

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers(RestTemplate restTemplate) {

        try {

            UserModel[] response = restTemplate.getForObject(jsonPlaceholderEndpoint, UserModel[].class);

            // Will iterate through the array of users and spit out data on each one
//            for (int i = 0; i < response.length; i++) {
//                UserModel user = response[i];
//                System.out.println(user.getName());
//            }

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            System.out.println(e.getClass());
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());

        }

    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getUserById(RestTemplate restTemplate, @PathVariable String id) {
        try {

            // throws a number format exception if id is not int
            Integer.parseInt(id);

            System.out.println("Getting user with id: " + id);

            String url = jsonPlaceholderEndpoint + "/" + id;

            UserModel response = restTemplate.getForObject(url, UserModel.class);

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

//    @GetMapping("/photos")
//    public Object photoHandler(RestTemplate restTemplate) {
//        String photoEndpoint = jsonPlaceholderEndpoint;
//        photoEndpoint += "photos";
//        return restTemplate.getForObject(photoEndpoint, Object.class);
//    }
//
//    @GetMapping("/posts")
//    public Object postsHandler(RestTemplate restTemplate) {
//        String postsEndpoint = jsonPlaceholderEndpoint;
//        postsEndpoint += "posts";
//        return restTemplate.getForObject(postsEndpoint, Object.class);
//    }
//
//    @GetMapping("/comments")
//    public Object commentsHandler(RestTemplate restTemplate) {
//        String commentsEndpoint = jsonPlaceholderEndpoint;
//        commentsEndpoint += "comments";
//        return restTemplate.getForObject(commentsEndpoint, Object.class);
//    }
//
//    @GetMapping("/albums")
//    public Object albumHandler(RestTemplate restTemplate) {
//        String albumsEndpoint = jsonPlaceholderEndpoint;
//        albumsEndpoint += "albums";
//        return restTemplate.getForObject(albumsEndpoint, Object.class);
//    }
//
//    @GetMapping("/users")
//    public Object userHandler(RestTemplate restTemplate) {
//        String userEndpoint = jsonPlaceholderEndpoint;
//        userEndpoint += "users";
//        return restTemplate.getForObject(userEndpoint, Object.class);
//    }
//
//    @GetMapping("/todos")
//    public Object todoHandler(RestTemplate restTemplate) {
//        String todoEndpoint = jsonPlaceholderEndpoint;
//        todoEndpoint += "todos";
//        return restTemplate.getForObject(todoEndpoint, Object.class);
//    }

}

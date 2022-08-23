package com.careerdevs.jsonplaceholder.controllers;

import com.careerdevs.jsonplaceholder.models.CommentModel;
import com.careerdevs.jsonplaceholder.models.UserModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final String jsonPHCommentEndpoint = "https://jsonplaceholder.typicode.com/comments";

    @GetMapping("/all")
    public ResponseEntity<?> getAllComments(RestTemplate restTemplate) {
        try {

            CommentModel[] response = restTemplate.getForObject(jsonPHCommentEndpoint, CommentModel[].class);

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            System.out.println(e.getClass());
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());

        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getCommentById(RestTemplate restTemplate, @PathVariable String id) {
        try {

            Integer.parseInt(id);

            System.out.println("Getting comment with id: " + id);

            String url = jsonPHCommentEndpoint + "/" + id;

            CommentModel response = restTemplate.getForObject(url, CommentModel.class);

            return ResponseEntity.ok(response);

        } catch (NumberFormatException e) {

            return ResponseEntity.status(400).body("ID " + id + " was not found in the database. Must be a whole number.");

        } catch (HttpClientErrorException.NotFound e) {

            return ResponseEntity.status(404).body("Comment Not Found With ID: " + id);

        } catch (Exception e) {

            System.out.println(e.getClass());
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());

        }
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteUserById(RestTemplate restTemplate, @PathVariable String id) {
        try {

            // throws a number format exception if id is not int
            Integer.parseInt(id);

            System.out.println("Getting user with id: " + id);

            String url = jsonPHCommentEndpoint + "/" + id;

            restTemplate.getForObject(url, UserModel.class);

            restTemplate.delete(url);

            return ResponseEntity.ok("User with ID " + id + " has been deleted.");

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

    // POST localhost:8080/api/users
    @PostMapping("/")
    public ResponseEntity<?> createNewUser(RestTemplate restTemplate, @RequestBody UserModel newUser) {
        try {

            //TODO: User data validation

            UserModel createdUser = restTemplate.postForObject(jsonPHCommentEndpoint, newUser, UserModel.class);


            return ResponseEntity.ok(createdUser);

        }  catch (Exception e) {

            System.out.println(e.getClass());
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    // PUT localhost:8080/api/users
    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateNewUser(RestTemplate restTemplate, @RequestBody UserModel updateUserData, @PathVariable String id) {
        try {

            Integer.parseInt(id);

            String url = jsonPHCommentEndpoint + "/" + id;

            //TODO: User data validation
            HttpEntity<UserModel> reqEntity = new HttpEntity<>(updateUserData);

//            restTemplate.put(url, updateUserData, UserModel.class);
            ResponseEntity<UserModel> jphRes =  restTemplate.exchange(url, HttpMethod.PUT, reqEntity ,UserModel.class);

            return ResponseEntity.ok(jphRes.getBody());

        }  catch (NumberFormatException e) {

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

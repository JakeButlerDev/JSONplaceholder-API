package com.careerdevs.jsonplaceholder.controllers;

import com.careerdevs.jsonplaceholder.models.UserModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final String jsonPHUserEndpoint = "https://jsonplaceholder.typicode.com/users";

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers(RestTemplate restTemplate) {

        try {

            UserModel[] response = restTemplate.getForObject(jsonPHUserEndpoint, UserModel[].class);

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

            String url = jsonPHUserEndpoint + "/" + id;

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

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteUserById(RestTemplate restTemplate, @PathVariable String id) {
        try {

            // throws a number format exception if id is not int
            Integer.parseInt(id);

            System.out.println("Getting user with id: " + id);

            String url = jsonPHUserEndpoint + "/" + id;

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

            UserModel createdUser = restTemplate.postForObject(jsonPHUserEndpoint, newUser, UserModel.class);


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

            String url = jsonPHUserEndpoint + "/" + id;

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

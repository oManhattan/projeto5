package com.timesete.projeto5.controller;

import com.timesete.projeto5.business.service.UserService;
import com.timesete.projeto5.model.dto.User.UserRequest;
import com.timesete.projeto5.model.dto.User.UserResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", exposedHeaders = "*")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody(required = true) UserRequest request) {
        try {
            UserResponse response = userService.createUser(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    
    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        try {
            return ResponseEntity.ok().body(userService.getAllUsers(PageRequest.of(page, size)));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/self/profile")
    public ResponseEntity<?> getUserProfile(@RequestHeader(required = true, name = "Authorization") String token) {
        try {
            UserResponse response = userService.getUserByToken(token);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/self/update")
    public ResponseEntity<?> updateUser(@RequestHeader(required = true, name = "Authorization") String token, @RequestBody(required = true) UserRequest request) {
        try {
            UserResponse response = userService.updateUserByToken(token, request);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping("/self/delete")
    public ResponseEntity<?> deleteUser(@RequestHeader("Authorization") String token) {
        try {
            userService.deleteUserByToken(token);
            return ResponseEntity.ok().body("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/third/profile")
    public ResponseEntity<?> getUserProfileById(@RequestParam(required = true, name = "id") String id) {
        try {
            UserResponse response = userService.getUserById(id);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/third/update")
    public ResponseEntity<?> updateUserById(@RequestParam(required = true, name = "id") String id, @RequestBody(required = true) UserRequest request) {
        try {
            UserResponse response = userService.updateUser(id, request);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping("/third/delete")
    public ResponseEntity<?> deleteUserById(@RequestParam(required = true, name = "id") String id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().body("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}

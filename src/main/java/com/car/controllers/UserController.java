package com.car.controllers;

import com.car.models.User;
import com.car.payload.ApiResponse;
import com.car.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all-users")
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        users = userService.getAllUsers();
        return users;
    }

    @PostMapping("/add-user")
    public ResponseEntity<ApiResponse<User>> addUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(ApiResponse.success("Added user", savedUser));
    }
}

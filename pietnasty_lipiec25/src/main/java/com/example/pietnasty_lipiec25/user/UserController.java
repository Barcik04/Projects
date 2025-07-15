package com.example.pietnasty_lipiec25.user;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@Valid @Positive @PathVariable int id) {
        userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    public User getUserById(@Valid @Positive @PathVariable int id) {
        return userService.findUserById(id);
    }
}

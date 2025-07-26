package com.example.pietnasty_lipiec25.user;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String order) {

        Sort sort = order.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        return userService.getAllUsers(sort);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User created = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@Valid @Positive @PathVariable int id) {
        userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    public User getUserById(@Valid @Positive @PathVariable int id) {
        return userService.findUserById(id);
    }

    @DeleteMapping("/all")
    public void deleteAllUsers() {
        userService.deleteAllUsers();
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user, @PathVariable int id) {
        User updatedUser = userService.updateUser(user, id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }
}

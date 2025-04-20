package com.shop.Barcik.Controller;

import com.shop.Barcik.jwt.JwtUtil;
import com.shop.Barcik.model.User;
import com.shop.Barcik.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.status(400).body("Username already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        System.out.println(user.getPassword());
        System.out.println(user.getUsername());



        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User foundUser = userRepository.findByUsername(user.getUsername());
        System.out.println("User:  " + foundUser);

        if (foundUser == null) {
            return ResponseEntity.status(400).body("Invalid username or password");
        }

        if (foundUser.getPassword().equals(user.getPassword())) {
            String token = jwtUtil.generateToken(foundUser.getUsername());
            return ResponseEntity.ok(token);
        }

        return ResponseEntity.status(400).body("Invalid username or password");
    }

    @DeleteMapping("/dev/clear-users")
    public ResponseEntity<?> clearAllUsers() {
        userRepository.deleteAll();
        System.out.println("USUNIĘCIE URUCHOMIONE");
        return ResponseEntity.ok("Wszyscy użytkownicy usunięci");
    }

}

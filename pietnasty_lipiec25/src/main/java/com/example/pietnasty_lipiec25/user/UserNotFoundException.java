package com.example.pietnasty_lipiec25.user;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int id) {
        super("User with id " + id + " not found");
    }
}

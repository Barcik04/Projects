package com.example.pietnasty_lipiec25.user;

import com.example.pietnasty_lipiec25.exception.DuplicateEmailExcepiton;
import com.example.pietnasty_lipiec25.exception.UserNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(Sort sort) {
        return userRepository.findAll(sort);
    }


    public User addUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateEmailExcepiton("User with email " + user.getEmail() + " already exists");
        }

        return userRepository.save(user);
    }

    public void deleteUser(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found"));

        userRepository.delete(user);
    }

    public User findUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found"));
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public User updateUser(User user, int id) {
         User existingUser = userRepository.findById(id)
                 .orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found"));

         if (userRepository.existsByEmail(user.getEmail()) && !existingUser.getEmail().equals(user.getEmail())) {
             throw new DuplicateEmailExcepiton("User with email " + user.getEmail() + " already exists");
         }

         user.setId(id);
         return userRepository.save(user);
    }
}

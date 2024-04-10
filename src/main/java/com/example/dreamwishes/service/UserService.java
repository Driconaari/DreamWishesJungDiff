package com.example.dreamwishes.service;

import com.example.dreamwishes.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create operation
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Read operation
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Update operation
    public User updateUser(Long id, SecurityProperties.User updatedUser) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            // Set other properties as needed
            return userRepository.save(user);
        } else {
            // Handle user not found
            return null;
        }
    }

    // Delete operation
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public boolean login(String username, String password) {

    }
}

package com.example.dreamwishes.service;

import com.example.dreamwishes.entity.Users;
import com.example.dreamwishes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Secured("ROLE_ADMIN")
    public void adminMethod() {
        // Method implementation
    }


    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create operation
    public Users createUser(Users user) {
        return userRepository.save(user);
    }

    // Read operation
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<Users> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Update operation
    public Users updateUser(Long id, Users updatedUser) {
        Optional<Users> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
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
        // Find user by username
        Users user = userRepository.findByUsername(username);

        // Check if user exists and if the password matches
        return user != null && user.getPassword().equals(password);
    }

    public Optional<Users> getUserId(String username) {
        // Your logic to retrieve the user by username
        Users user = userRepository.findByUsername(username);
        return Optional.ofNullable(user);
    }

}
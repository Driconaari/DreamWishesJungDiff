package com.example.dreamwishes.service;

import com.example.dreamwishes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean login(String username, String password) {
        // Perform authentication logic
        User user = (User) userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return true; // Authentication successful
        }
        return false; // Authentication failed
    }

    public Integer getUserId(String username) {
        // Retrieve user ID based on username from the database
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return user.getUserID(); // Return user ID if found
        }
        return null; // User not found
    }


    // Add more methods for user registration, fetching user details, updating user information, etc.
}


package com.example.dreamwishes.service;

import com.example.dreamwishes.entity.Users;
import com.example.dreamwishes.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {



    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Create operation
   public Users createUser(Users user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    Users savedUser = null;
    try {
        logger.info("Attempting to create user: {}", user.getUsername());
        savedUser = userRepository.save(user);
        logger.info("Created new user: {}", savedUser.getUsername());
    } catch (Exception e) {
        logger.error("Error creating user: ", e);
    }
    return savedUser;
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
        user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        // Set other properties as needed
        return userRepository.save(user);
    } else {
        // Handle user not found
        return null;
    }
}


    public boolean login(String username, String password) {
        // Find user by username
        Users user = userRepository.findByUsername(username);

        // Check if user exists and if the password matches
        if (user != null) {
            return passwordEncoder.matches(password, user.getPassword());
        } else {
            // Handle user not found
            return false;
        }
    }

    // Delete operation
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

public Optional<Users> getUserByUsername(String username) {
    return Optional.ofNullable(userRepository.findByUsername(username));
}

    public Optional<Long> getUserId(String username) {
    // Retrieve the user by username
    Users user = userRepository.findByUsername(username);

    // If the user exists, return their ID
    if (user != null) {
        return Optional.of(user.getUserID());
    } else {
        // If the user doesn't exist, return an empty Optional
        return Optional.empty();
    }


}




//old get user
    /*public Optional<Users> getUserId(String username) {
        // Your logic to retrieve the user by username
        Users user = userRepository.findByUsername(username);
        return Optional.ofNullable(user);
    }

     */
/*
    // Retrieve wishes for the logged-in user
    public List<WishesEntity> getLoggedInUserWishes() {
        // Get the username of the logged-in user
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();

        // Retrieve the user object using the username
        Users user = userRepository.findByUsername(username);

        // Return wishes associated with the user
        return user.getWishes();
    }

 */
}
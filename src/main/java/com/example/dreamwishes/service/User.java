package com.example.dreamwishes.service;

import java.util.HashMap;
import java.util.Map;

public class User {

    private static int nextID = 1; // static variable to generate unique user IDs
    private int userID;
    private String username;
    private String email;
    private String password;

    // Map to store users by their username for easy lookup
    private static Map<String, User> usersByUsername = new HashMap<>();

    public User(String username, String email, String password) {
        this.userID = nextID++; // assign unique user ID
        this.username = username;
        this.email = email;
        this.password = password;
        usersByUsername.put(username, this); // add user to the map
    }

    // Method to create a new user
    public User createUser(String username, String email, String password) {
        if (usersByUsername.containsKey(username)) {
            System.out.println("Username already exists.");
            return null; // if the user already exists
        }
        return new User(username, email, password);
    }

    // Method for handling user login
    public User login(String username, String password) {
        if (usersByUsername.containsKey(username)) {
            User user = usersByUsername.get(username);
            if (user.getPassword().equals(password)) {
                return user;
            } else {
                System.out.println("Incorrect password.");
                return null; // incorrect password
            }
        } else {
            System.out.println("User not found");
            return null;
        }
    }

    // Getters and setters
    public static int getNextID() {
        return nextID;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public static Map<String, User> getUsersByUsername() {
        return usersByUsername;
    }

    public static void setNextID(int nextID) {
        User.nextID = nextID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void setUsersByUsername(Map<String, User> usersByUsername) {
        User.usersByUsername = usersByUsername;
    }


}

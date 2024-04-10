package com.example.dreamwishes.controller;

import com.example.dreamwishes.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.Group;
import org.apache.catalina.Role;
import org.apache.catalina.User;
import org.apache.catalina.UserDatabase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Iterator;

@Controller
public class UserController {

    @GetMapping("/login")
    public String showLoginPage() {
        // Logic to show the login page
        return "login";
    }
//old method for login

    /*
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        // Logic to handle user login
        // You can implement authentication logic here
        // If login successful, redirect to dashboard
        // If login fails, redirect back to login page with error message
        return "redirect:/";
    }

     */

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session,
                        Model model) {

        // Instantiate UserService
        UserService userService = new UserService();

        // Perform login logic to authenticate user
        if (userService.login(username, password)) {
            // Set login status to true and store user ID in session
            session.setAttribute("loggedIn", true);
            session.setAttribute("userId", userService.getUserId(username));

            // Redirect to index page after successful login
            return "redirect:/";
        } else {
            // If login fails, return to login page with error message
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        // Logic to show the registration page
        // You can pass any necessary data to the registration form
        model.addAttribute("user", new User() {
            @Override
            public boolean equals(Object another) {
                return false;
            }

            @Override
            public String toString() {
                return null;
            }

            @Override
            public int hashCode() {
                return 0;
            }

            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getFullName() {
                return null;
            }

            @Override
            public void setFullName(String s) {

            }

            @Override
            public Iterator<Group> getGroups() {
                return null;
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public void setPassword(String s) {

            }

            @Override
            public Iterator<Role> getRoles() {
                return null;
            }

            @Override
            public UserDatabase getUserDatabase() {
                return null;
            }

            @Override
            public String getUsername() {
                return null;
            }

            @Override
            public void setUsername(String s) {

            }

            @Override
            public void addGroup(Group group) {

            }

            @Override
            public void addRole(Role role) {

            }

            @Override
            public boolean isInGroup(Group group) {
                return false;
            }

            @Override
            public boolean isInRole(Role role) {
                return false;
            }

            @Override
            public void removeGroup(Group group) {

            }

            @Override
            public void removeGroups() {

            }

            @Override
            public void removeRole(Role role) {

            }

            @Override
            public void removeRoles() {

            }
        });
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        // Logic to handle user registration
        // You can validate user input and save user to database
        // Redirect to login page after successful registration
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String showUserProfile(Model model) {
        // Logic to retrieve user profile data
        // You can pass user data to the profile page
        User currentUser = getCurrentUser(); // Example method to get current user
        model.addAttribute("user", currentUser);
        return "profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute User updatedUser) {
        // Logic to update user profile
        // You can update user data in the database
        return "redirect:/profile";
    }

    private User getCurrentUser() {
        // Dummy method to get current user (replace with actual implementation)
        return new User() {
            @Override
            public boolean equals(Object another) {
                return false;
            }

            @Override
            public String toString() {
                return null;
            }

            @Override
            public int hashCode() {
                return 0;
            }

            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getFullName() {
                return null;
            }

            @Override
            public void setFullName(String s) {

            }

            @Override
            public Iterator<Group> getGroups() {
                return null;
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public void setPassword(String s) {

            }

            @Override
            public Iterator<Role> getRoles() {
                return null;
            }

            @Override
            public UserDatabase getUserDatabase() {
                return null;
            }

            @Override
            public String getUsername() {
                return null;
            }

            @Override
            public void setUsername(String s) {

            }

            @Override
            public void addGroup(Group group) {

            }

            @Override
            public void addRole(Role role) {

            }

            @Override
            public boolean isInGroup(Group group) {
                return false;
            }

            @Override
            public boolean isInRole(Role role) {
                return false;
            }

            @Override
            public void removeGroup(Group group) {

            }

            @Override
            public void removeGroups() {

            }

            @Override
            public void removeRole(Role role) {

            }

            @Override
            public void removeRoles() {

            }
        };
    }
}

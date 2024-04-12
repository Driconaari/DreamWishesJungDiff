package com.example.dreamwishes.controller;

import com.example.dreamwishes.entity.Users;
import com.example.dreamwishes.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginPage() {
        // Logic to show the login page
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session,
                        Model model) {

        if (userService.login(username, password)) {
            session.setAttribute("loggedIn", true);
            session.setAttribute("userId", userService.getUserId(username).orElse(null));
            return "redirect:/";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }


    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new Users());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Users user) {
        userService.createUser(user);
        return "redirect:/login";
    }
    @GetMapping("/profile")
    public String showUserProfile(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            Users currentUser = userService.getUserById(userId).orElse(null);
            model.addAttribute("user", currentUser);
            return "profile";
        } else {
            return "redirect:/login";
        }
    }
    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute Users updatedUser) {
        userService.updateUser(updatedUser.getUserID(), updatedUser);
        return "redirect:/profile";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);
        return "dashboard";
    }
}



package com.example.dreamwishes.controller;

import com.example.dreamwishes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;
/*
    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        String username = principal.getName();
        Optional<Users> user = userService.getUserId(username);
        if (user.isPresent()) {
            model.addAttribute("loggedInUser", user.get());
            model.addAttribute("userWishes", user.get().getWishes()); // Assuming there's a method to get wishes
            return "profile";
        } else {
            // Handle case where user is not found
            return "error";
        }
    }

 */
}

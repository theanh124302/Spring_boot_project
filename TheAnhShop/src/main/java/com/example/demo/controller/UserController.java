package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam; // Correct import for RequestParam
import com.example.demo.dto.User;
import com.example.demo.service.UserService;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "index.html";
    }
    @GetMapping("/user/search")
    public String searchByUsername(@RequestParam("username") String username, Model model) {
        User user = userService.getUser(username);
        model.addAttribute("user", user);
        return "user.html";
    }
}
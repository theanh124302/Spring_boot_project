package com.example.demo.controller;


import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.example.demo.dto.User;
import com.example.demo.service.UserService;

@Controller
public class LoginController {
	@Autowired
	UserService userService;
    @GetMapping("/login")
    public String showLoginPage() {
        return "login.html";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session,
                        Model model) {
    	List<User> users = userService.getAll();
    	for (User user : users) {
    		if(username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                model.addAttribute("username",user.getUsername());
                return "home.html";
    		}
    	}
        model.addAttribute("loginFailed", true);
        return "login.html";
    }
}

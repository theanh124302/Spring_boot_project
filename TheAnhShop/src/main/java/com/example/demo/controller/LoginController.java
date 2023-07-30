package com.example.demo.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
	@GetMapping("/login")
	public String hi() {
		return "login.html";
	}
	@PostMapping("/login")
	public String login(HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp,
			jakarta.servlet.http.HttpSession session,
			@RequestParam("username") String username,
			@RequestParam("password") String password
			) throws IOException {
		if(username.equals("admin")&&password.equals("123")) {
			session.setAttribute("loginUser", username);
			return "home.html";
		}else {
			return "redirect:/login";
		}
	}
}
